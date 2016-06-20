package com.github.drxaos.profiler.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class ExampleServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9090);

        ContextHandler context0 = new ContextHandler();
        context0.setContextPath("/");

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(ExampleServlet.class, "/*");

        server.start();
        server.join();
    }
}
