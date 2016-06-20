package com.github.drxaos.profiler.example;

import javax.servlet.http.HttpServletRequest;

public class ExampleService {

    public String handle(HttpServletRequest httpServletRequest) {

        for (int i = 0; i < 5; i++) {
            func1();
        }

        new Sleeper().exec(1000);

        return "ok";

    }

    private void func1() {
        new Sleeper().exec(100);

    }

}
