package ru.nsu.fit.ojp.ushaev.ojp_2;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // Builds the byte array representation of the class
        // Automatically computes local vars and operands stack sizes
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        // Defines the class header
        cw.visit(V11, ACC_PUBLIC, "GuessNumber",
                null, "java/lang/Object", null);

        // Default constructor
        MethodVisitor constructor = cw.visitMethod(ACC_PUBLIC, "<init>",
                "()V", null, null);

        // Start bytecode generation
        constructor.visitCode();

        // Push 'this' on the operand stack
        constructor.visitVarInsn(ALOAD, 0);

        // Call constructor from java/lang/Object
        constructor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object",
                "<init>", "()V", false);

        // Visit zero-operand instruction return
        constructor.visitInsn(RETURN);

        // Ignored because of compute_maxs
        constructor.visitMaxs(0, 0);

        // End method generation
        constructor.visitEnd();

        // psvm
        MethodVisitor main = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null);

        main.visitCode();

        // call Math.random
        main.visitMethodInsn(INVOKESTATIC, "java/lang/Math",
                "random", "()D", false);

        // push 100 on operand stack
        main.visitLdcInsn(100.0d);

        // perform double multiplication
        main.visitInsn(DMUL);

        // store 1.0d on operand stack
        main.visitInsn(DCONST_1);

        // perform double addition
        main.visitInsn(DADD);

        // cast double to int
        main.visitInsn(D2I);

        // save generated number to local variables stack
        main.visitVarInsn(ISTORE, 1);

        // create new object
        main.visitTypeInsn(NEW, "java/util/Scanner");

        // duplicate uninitialized scanner reference, one to store, one to call <init>
        // constructor returns void, so one of the references just pops from stack
        // so, second reference is used to copy object to local variables stack
        main.visitInsn(DUP);

        // get System.in for Scanner constructor
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");

        // call scanner constructor
        main.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner",
                "<init>", "(Ljava/io/InputStream;)V", false);

        // store initialized scanner reference
        main.visitVarInsn(ASTORE, 2);

        // Initialize static System.out object, place it om operand stack
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        main.visitLdcInsn("I've thought a number, try to guess!");
        main.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V", false);

        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();

        main.visitLabel(l1);

        // Initialize static System.out object, place it om operand stack
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        main.visitLdcInsn("Enter a guess:");
        main.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V", false);

        // move initialized scanner reference to operands stack
        main.visitVarInsn(ALOAD, 2);

        // call scanner.nextInt()
        main.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner",
                "nextInt", "()I", false);

        // move scanned integer to local variables stack
        main.visitVarInsn(ISTORE, 3);

        // move scanned integer to operand stack
        main.visitVarInsn(ILOAD, 3);

        // move generated value to operand stack
        main.visitVarInsn(ILOAD, 1);


        main.visitJumpInsn(IF_ICMPNE, l2);
        // if equals
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        main.visitLdcInsn("Your guess is correct. Congratulations!");
        main.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V", false);
        main.visitJumpInsn(GOTO, l3);


        main.visitLabel(l2);
        // move scanned integer to operand stack
        main.visitVarInsn(ILOAD, 3);
        // move generated value to operand stack
        main.visitVarInsn(ILOAD, 1);
        main.visitJumpInsn(IF_ICMPGE, l4);
        // if smaller
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        main.visitLdcInsn("Lower.");
        main.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V", false);
        main.visitJumpInsn(GOTO, l3);


        main.visitLabel(l4);
        main.visitVarInsn(ILOAD, 3);
        // move generated value to operand stack
        main.visitVarInsn(ILOAD, 1);
        main.visitJumpInsn(IF_ICMPLE, l3);
        // if greater
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        main.visitLdcInsn("Greater.");
        main.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V", false);
        main.visitJumpInsn(GOTO, l3);


        main.visitLabel(l3);
        // move scanned integer to operand stack
        main.visitVarInsn(ILOAD, 3);
        // move generated value to operand stack
        main.visitVarInsn(ILOAD, 1);
        main.visitJumpInsn(IF_ICMPNE, l1);

        main.visitInsn(RETURN);

        // Initialize static System.out object, place it om operand stack
        main.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        main.visitVarInsn(ILOAD, 1);

        // Invoke println method from stack + constant
        main.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(I)V", false);

        // Visit zero-operand instruction return
        main.visitInsn(RETURN);

        // Ignored because of compute_maxs
        // Used to set local variables and operand stack sizes
        main.visitMaxs(0, 0);

        // End method generation
        main.visitEnd();

        // Save as .class file
        FileOutputStream out = new FileOutputStream("GuessNumber.class");
        out.write(cw.toByteArray());
        out.close();
    }
}
