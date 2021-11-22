package com.grandrain.tetris.logic;

public record ViewData(int[][] brickData, int xPosition, int yPosition, int[][] nextBrickData) {

    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

}
