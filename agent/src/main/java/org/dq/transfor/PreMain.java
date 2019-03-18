package org.dq.transfor;

import java.lang.instrument.Instrumentation;

public class PreMain {
    public static void premain(String agentArgs, Instrumentation _inst) {
        _inst.addTransformer(new MyTransfor(), true);//需要第二个参数
    }
}
