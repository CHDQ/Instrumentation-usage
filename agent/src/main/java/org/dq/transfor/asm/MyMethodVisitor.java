package org.dq.transfor.asm;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class MyMethodVisitor extends MethodVisitor {
    public MyMethodVisitor(MethodVisitor methodVisitor) {
        super(ASM7, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitMethodInsn(INVOKESTATIC, );
    }
}
