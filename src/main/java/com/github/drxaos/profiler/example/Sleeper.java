package com.github.drxaos.profiler.example;

public class Sleeper {

    void exec(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
