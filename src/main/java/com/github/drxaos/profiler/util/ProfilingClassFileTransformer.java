package com.github.drxaos.profiler.util;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ProfilingClassFileTransformer implements ClassFileTransformer {

    private ClassPool pool;

    public ProfilingClassFileTransformer() {
        pool = ClassPool.getDefault();
    }

    public byte[] transform(ClassLoader loader, String className,
                            Class classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null) {
            return classfileBuffer;
        }
        try {
            pool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
            CtClass cclass = pool.get(className.replaceAll("/", "."));
            if (className.startsWith("com/github/drxaos/profiler/example/") && !cclass.isFrozen()) {
                for (CtMethod currentMethod : cclass.getDeclaredMethods()) {
                    if (!Modifier.isNative(currentMethod.getModifiers())) {
                        currentMethod.insertBefore(createJavaString("start", currentMethod, className));
                        currentMethod.insertAfter(createJavaString("end", currentMethod, className), true);
                    }
                }
                return cclass.toBytecode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createJavaString(String type, CtMethod currentMethod, String className) {
        String info = ProfilerInfo.class.getName();
        return "{" + info + "." + type + "(\"" + className + "\",\"" + currentMethod.getName() + "\");}";
    }
}