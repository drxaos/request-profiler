package com.github.drxaos.profiler.example;

import javax.servlet.http.HttpServletRequest;

public class ExampleService {

    public String handle(HttpServletRequest httpServletRequest) {

        new Sleeper().exec(1000);

        return "ok";

    }

}
