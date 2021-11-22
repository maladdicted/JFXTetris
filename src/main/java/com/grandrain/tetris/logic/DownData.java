package com.grandrain.tetris.logic;

public record DownData(ClearRow clearRow, ViewData viewData) {

    public ViewData getViewData() {
        return viewData;
    }

}
