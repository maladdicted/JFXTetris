package com.grandrain.tetris.logic.rotator;

import com.grandrain.tetris.logic.MatrixOperations;

public record NextShapeInfo(int[][] shape, int position) {

    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    public int getPosition() {
        return position;
    }
}
