package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class CollectionString {
    public static void main(String[] args) {
        Collection<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("Java");
        strings.add("C++");
        strings.add("PHP");
        strings.add("Python");
        strings.add("\uD83D\uDD25");

// записываем  в бинарный файл коллекцию строк (String)
        try (FileOutputStream stream = new FileOutputStream("outputString.bin")) {
            writeStrings(strings, stream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//читаем из бинарного файла коллекцию строк (String) и выводим их на консоль
        try (FileInputStream stream = new FileInputStream("outputString.bin")) {
            Collection<String> newStrings = readStrings(stream);
            for (String s : newStrings) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private static void writeStrings(Collection<String> strings,
                                     FileOutputStream output) throws IOException {
        try (DataOutputStream stream = new DataOutputStream(output)) {
            stream.writeShort(strings.size());
            for (String item : strings) {
                stream.writeUTF(item);
            }
        }
    }


    private static Collection<String> readStrings(FileInputStream input) throws IOException {
        Collection<String> strings = new ArrayList<>();
        try (DataInputStream stream = new DataInputStream(input)) {
            String item;
            for (short stringsCount = stream.readShort(); stringsCount > 0; stringsCount--) {
                item = stream.readUTF();
                strings.add(item);
            }
        }
        return strings;
    }
}
