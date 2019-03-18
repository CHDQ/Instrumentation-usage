package org.dq.transfor.asm;

import org.joda.time.DateTime;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;


import static org.objectweb.asm.Opcodes.*;

public class MyMethodVisitor extends MethodVisitor {
    public MyMethodVisitor(MethodVisitor methodVisitor) {
        super(ASM7, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitFieldInsn(GETFIELD, "java/lang/System", "out", "Ljava/io/PrintStream;");
        super.visitMethodInsn(INVOKESTATIC, "org/joda/time/DateTime", "now", "()Lorg/joda/time/DateTime;", false);
        super.visitLdcInsn("yyyy-MM-dd HH:mm:ss");
        super.visitMethodInsn(INVOKEVIRTUAL, "org/joda/time/DateTime", "toString", "(Ljava/lang/String;)V", false);
        Handle bootstrap = new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants",
                "(Ljava/lang/invoke/MethodHandles/Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;" +
                        "Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false);
        super.visitInvokeDynamicInsn("makeConcatWithConstants", "(Ljava/lang/String;)Ljava/lang/String;", bootstrap, "[begin]:");
        super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }


    @Override
    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
            super.visitFieldInsn(GETFIELD, "java/lang/System", "out", "Ljava/io/PrintStream;");
            super.visitMethodInsn(INVOKESTATIC, "org/joda/time/DateTime", "now", "()Lorg/joda/time/DateTime;", false);
            super.visitLdcInsn("yyyy-MM-dd HH:mm:ss");
            super.visitMethodInsn(INVOKEVIRTUAL, "org/joda/time/DateTime", "toString", "(Ljava/lang/String;)V", false);
            Handle bootstrap = new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants",
                    "(Ljava/lang/invoke/MethodHandles/Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;" +
                            "Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false);
            super.visitInvokeDynamicInsn("makeConcatWithConstants", "(Ljava/lang/String;)Ljava/lang/String;",
                    bootstrap, "[end]:");
            super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
    }
}
