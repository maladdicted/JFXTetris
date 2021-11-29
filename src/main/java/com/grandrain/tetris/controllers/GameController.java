package com.grandrain.tetris.controllers;

import com.grandrain.tetris.Audio;
import com.grandrain.tetris.logic.*;
import com.grandrain.tetris.logic.events.EventSource;
import com.grandrain.tetris.logic.events.InputEventListener;
import com.grandrain.tetris.logic.events.MoveEvent;

public class GameController implements InputEventListener {

    private final Board board = new SimpleBoard(25, 10);

    private final MainController mainController;

    public GameController(MainController c) {
        mainController = c;
        board.createNewBrick();
        mainController.setEventListener(this);
        mainController.initGameView(board.getBoardMatrix(), board.getViewData());
        mainController.bindScore(board.getScore().scoreProperty());
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
                Audio.playRowClear();
            }
            if (board.createNewBrick()) {
                mainController.gameOver();
            }

            mainController.refreshGameBackground(board.getBoardMatrix());
            Audio.playBrickHit();
        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
                Audio.playForceHit();
            }
        }
        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        Audio.playBrickRotate();
        return board.getViewData();
    }

    @Override
    public void createNewGame() {
        board.newGame();
        mainController.refreshGameBackground(board.getBoardMatrix());
    }
}