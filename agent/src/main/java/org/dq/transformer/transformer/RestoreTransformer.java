package org.dq.transformer.transformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 用于还原agent的修改
 */
public class RestoreTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.endsWith("Test")) {
            return classfileBuffer;
        }
        try {
//            return readStream(ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class"), true);
            /**
             * asm的方式修改
             */
            ClassReader classReader = new ClassReader(className);
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            classReader.accept(classWriter, 0);
            return classWriter.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }

    /**
     * 还原agent的修改，测试过，可以使用
     *
     * @param inputStream
     * @param close
     * @return
     * @throws IOException
     */
    private static byte[] readStream(InputStream inputStream, boolean close) throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        } else {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] data = new byte[4096];

                int bytesRead;
                while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, bytesRead);
                }

                outputStream.flush();
                byte[] var5 = outputStream.toByteArray();
                return var5;
            } finally {
                if (close) {
                    inputStream.close();
                }

            }
        }
    }

}
