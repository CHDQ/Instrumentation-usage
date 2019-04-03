package org.dq.transformer;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * 运行时调用
 * java -jar agent.jar 16285 org.dq.Test
 * pid： 16285 目标java线程id
 * org.dq.Test ： 目标类名
 */
public class JVMThread {
    private static String className;
    public static final String CLOSE_COMMAND = "REDUCTION;";
    private static String pid;

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        if (args.length != 2) {
            System.err.println("must point pid and class");
            return;
        }
        JVMThread.doShutDownWork();
        JVMThread.className = args[1];
        JVMThread.pid = args[0];
        VirtualMachine virtualMachine = VirtualMachine.attach(JVMThread.pid);
        String os = System.getProperty("os.name");
        String agentJarPath = JVMThread.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (os.toLowerCase().startsWith("win")) {//windows文件路径中开始会有/
            agentJarPath = agentJarPath.substring(1);
        }
        virtualMachine.loadAgent(agentJarPath, JVMThread.className);
        virtualMachine.detach();
        System.out.println("attach running ...");
        while (true) ;
    }

    private static void doShutDownWork() {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> {
            try {
                VirtualMachine virtualMachine = VirtualMachine.attach(JVMThread.pid);
                String agentJarPath = JVMThread.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                String os = System.getProperty("os.name");
                if (os.toLowerCase().startsWith("win")) {//windows文件路径中开始会有/
                    agentJarPath = agentJarPath.substring(1);
                }
                virtualMachine.loadAgent(agentJarPath, CLOSE_COMMAND + JVMThread.className);
                virtualMachine.detach();
            } catch (AgentLoadException e) {
                e.printStackTrace();
            } catch (AgentInitializationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AttachNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println("REDUCTION!");
        }));
    }
}
