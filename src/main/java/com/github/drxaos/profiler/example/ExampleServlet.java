package com.github.drxaos.profiler.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExampleServlet extends HttpServlet {

    ExampleService exampleService = new ExampleService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        new Sleeper().exec(2000);

        exampleService.handle(request);

        response.getWriter().write("Hello!");

    }


}
