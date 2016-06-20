package com.github.drxaos.profiler.example;

import com.github.drxaos.profiler.util.ProfilingFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ExampleServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9090);

        ContextHandler context0 = new ContextHandler();
        context0.setContextPath("/");

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addFilterWithMapping(ProfilingFilter.class, "/*",
                EnumSet.of(DispatcherType.REQUEST));

        handler.addServletWithMapping(ExampleServlet.class, "/*");

        server.start();
        server.join();
    }
}
