package com.grandrain.tetris.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Scores {

    public static String read() throws IOException {
        String scores = "";
        File file = new File("scores.txt");

        if (!file.createNewFile()) {
            scores = String.join("\n", Files.readAllLines(file.toPath()));
        }

        return scores;
    }

    public static void write(String score) throws IOException {
        File file = new File("scores.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        String datetime = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(LocalDateTime.now());
        String user = System.getProperty("user.name");
        String record = "[" + datetime + "] " + user + ": " + score + "\n";
        Files.write(file.toPath(), record.getBytes(), StandardOpenOption.APPEND);
    }

    public static void clear() throws IOException {
        Files.write(Paths.get("scores.txt"), "".getBytes());
    }
}
