package com.grandrain.tetris;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

public final class Data {

    private static Wini wini;

    public static void reload() throws IOException {
        File file = new File("data.ini");
        if (file.createNewFile()) {
            wini = new Wini();
            wini.add("config", "effects", 0.1);
            wini.add("config", "music", 0.1);
            wini.store(file);
        } else {
            wini = new Wini(file);
        }
    }

    public static double getEffectsVolume() { return getValue("effects"); }

    public static double getMusicVolume() { return getValue("music"); }

    public static void applyConfig(double effectsVolume, double musicVolume) throws IOException {
        wini.put("config", "effects", effectsVolume);
        wini.put("config", "music", musicVolume);
        wini.store();
    }

    private static double getValue(String key) {
        double value = 0.1;
        try { value = wini.get("config", key, double.class); }
        finally { return value; }
    }
}
