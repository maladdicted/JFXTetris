package com.grandrain.tetris.logic.events;

import com.grandrain.tetris.logic.ViewData;
import com.grandrain.tetris.logic.DownData;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event);

    ViewData onLeftEvent(MoveEvent event);

    ViewData onRightEvent(MoveEvent event);

    ViewData onRotateEvent(MoveEvent event);

    void createNewGame();
}
