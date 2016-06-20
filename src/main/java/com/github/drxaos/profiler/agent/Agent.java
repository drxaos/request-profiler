package com.github.drxaos.profiler.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class Agent {
    private static Instrumentation instrumentation;

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    public static void addTransformer(ClassFileTransformer transformer) {
        instrumentation.addTransformer(transformer);
    }

}
