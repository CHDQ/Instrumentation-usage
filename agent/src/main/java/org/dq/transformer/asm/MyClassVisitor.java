package org.dq.transformer.asm;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class MyClassVisitor extends ClassVisitor {
    private boolean isInterface;

    public MyClassVisitor(ClassVisitor classVisitor) {
        super(ASM7, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        isInterface = (access & ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        if (!isInterface && methodVisitor != null && !name.equals("<init>")) {
            methodVisitor = new MyMethodVisitor(methodVisitor);
        }
        return methodVisitor;
    }
}
