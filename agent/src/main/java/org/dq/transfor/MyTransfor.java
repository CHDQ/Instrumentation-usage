package org.dq.transfor;

import org.dq.transfor.asm.MyClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyTransfor implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.endsWith("Test")) {
            return classfileBuffer;
        }
        try {
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            MyClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
            classReader.accept(myClassVisitor, 0);
            return classWriter.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
