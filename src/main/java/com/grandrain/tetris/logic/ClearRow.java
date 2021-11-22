package com.grandrain.tetris.logic;

public record ClearRow(int linesRemoved, int[][] newMatrix, int scoreBonus) {

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public int[][] getNewMatrix() {
        return MatrixOperations.copy(newMatrix);
    }

    public int getScoreBonus() {
        return scoreBonus;
    }
}
