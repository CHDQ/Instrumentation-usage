package org.dq.transformer;

import org.dq.transformer.transformer.ModifyTransformer;
import org.dq.transformer.transformer.RestoreTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import static org.dq.transformer.JVMThread.CLOSE_COMMAND;

/**
 * 运行期使用
 */
public class AgentMain {

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        try {
            if (agentArgs == null) {
                System.err.println("must point redefineClass");
                return;
            }
            System.out.println("Agent Main called!");
            System.out.println("Agent args : " + agentArgs);
            String className = agentArgs.replace(CLOSE_COMMAND, "");
            Class<?> aClass = Class.forName(className);
            if (!agentArgs.startsWith(CLOSE_COMMAND)) {
                System.out.println("add time generate , className:  " + className);
                instrumentation.addTransformer(new ModifyTransformer(), true);//第二参数必须加，否则无法修改字节码
            } else {
                System.out.println("remove time generate , className:  " + className);
                instrumentation.addTransformer(new RestoreTransformer(), true);
            }
            instrumentation.retransformClasses(aClass);
            System.out.println("agent operate finished");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }
}
