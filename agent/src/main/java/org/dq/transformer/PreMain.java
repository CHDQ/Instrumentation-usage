package org.dq.transformer;

import org.dq.transformer.transformer.ModifyTransformer;

import java.lang.instrument.Instrumentation;

/**
 * java运行时参数调用
 */
public class PreMain {
    public static void premain(String agentArgs, Instrumentation _inst) {
        _inst.addTransformer(new ModifyTransformer(), true);//需要第二个参数
    }
}
