package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream("out1.bin"))) {
            stream.writeBoolean(true);
            stream.writeInt(12367);
            stream.writeChar('f');
            stream.writeShort(35);
            stream.writeFloat(66.67f);
            stream.writeDouble(66.67678);
            stream.writeUTF("Java");
            stream.writeUTF("Джава");
            System.out.println("File has been written");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream stream = new DataInputStream(new FileInputStream("out1.bin"))) {
            boolean b = stream.readBoolean();
            int i = stream.readInt();
            char c = stream.readChar();
            short sh = stream.readShort();
            float f = stream.readFloat();
            double d = stream.readDouble();
            String s1 = stream.readUTF();
            String s2 = stream.readUTF();
            System.out.println("Print:");
            System.out.printf("Boolean: %b \t Int: %d \t Char: %s \t Short: %d \t Float: %f \t Double: %f \t String1: %s \t String2: %s ", b, i, c, sh, f, d, s1, s2);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
