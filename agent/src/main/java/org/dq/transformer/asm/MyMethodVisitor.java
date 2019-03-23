package org.dq.transformer.asm;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.ASMifier;


import static org.objectweb.asm.Opcodes.*;

/**
 * 修改方法，可以用ASMifier工具反向输出字节码
 */
public class MyMethodVisitor extends MethodVisitor {
    public MyMethodVisitor(MethodVisitor methodVisitor) {
        super(ASM7, methodVisitor);
    }

    /**
     * 开始
     */
    @Override
    public void visitCode() {
        super.visitCode();
        super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        super.visitMethodInsn(INVOKESTATIC, "org/joda/time/DateTime", "now", "()Lorg/joda/time/DateTime;", false);
        super.visitLdcInsn("yyyy-MM-dd HH:mm:ss");
        super.visitMethodInsn(INVOKEVIRTUAL, "org/joda/time/DateTime", "toString", "(Ljava/lang/String;)Ljava/lang/String;", false);
        Handle bootstrap = new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants",
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false);
        super.visitInvokeDynamicInsn("makeConcatWithConstants", "(Ljava/lang/String;)Ljava/lang/String;", bootstrap, "[begin]:\u0001");
        super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    /**
     * 结束
     *
     * @param opcode
     */
    @Override
    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
            super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            super.visitMethodInsn(INVOKESTATIC, "org/joda/time/DateTime", "now", "()Lorg/joda/time/DateTime;", false);
            super.visitLdcInsn("yyyy-MM-dd HH:mm:ss");
            super.visitMethodInsn(INVOKEVIRTUAL, "org/joda/time/DateTime", "toString", "(Ljava/lang/String;)Ljava/lang/String;", false);
            Handle bootstrap = new Handle(H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants",
                    "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false);
            super.visitInvokeDynamicInsn("makeConcatWithConstants", "(Ljava/lang/String;)Ljava/lang/String;",
                    bootstrap, "[end]:\u0001");
            super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
    }
}
