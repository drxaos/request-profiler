package com.github.drxaos.profiler.agent;

import java.lang.instrument.Instrumentation;

public class ProfilerAgent {
    public static void premain(String agentArgument, Instrumentation instrumentation) {
        instrumentation.addTransformer(new ProfilingClassFileTransformer());
    }
}
