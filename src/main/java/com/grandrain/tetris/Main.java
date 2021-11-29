package com.grandrain.tetris;

import com.grandrain.tetris.data.Config;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config.read();
        App.main();
    }
}
