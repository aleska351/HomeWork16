package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class ContactDemo {
    public static void main(String[] args) throws IOException {
        Collection<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Alex", "111", 25));
        contacts.add(new Contact("Oleg", "112", 20));
        contacts.add(new Contact("Dima", "113", 30));
        contacts.add(new Contact("Igor", "114", 17));
        writeContactSerial(contacts);
        readContactSerial();

    //Записываем  в бинарный файл коллекцию контактов (Contact)  используя DataOutputStream.
        try (FileOutputStream stream = new FileOutputStream("outputContacts.bin")) {
            writeContactDOS(contacts, stream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Читаем из бинарного файла коллекцию контактов (Contact)  используя  DataInputStream.
        try (FileInputStream stream = new FileInputStream("outputContacts.bin")) {
            Collection<Contact> newContacts = readContactDIS(stream);
            for (Contact contact : newContacts) {
                System.out.println(contact);
            }
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private static void writeContactDOS(Collection<Contact> contacts,
                                     FileOutputStream output) throws IOException {
        try (DataOutputStream stream = new DataOutputStream(output)) {
            stream.writeShort(contacts.size());
            for (Contact contact : contacts) {
                stream.writeUTF(contact.getName());
                stream.writeUTF(contact.getPhoneNumber());
                stream.writeInt(contact.getAge());
            }
        }
    }


    private static Collection<Contact> readContactDIS(FileInputStream input) throws IOException {
        Collection<Contact> contacts = new ArrayList<>();
        try (DataInputStream stream = new DataInputStream(input)) {

            short stringsCount = stream.readShort();
            while (stringsCount > 0) {
                String contactName = stream.readUTF();
                String phoneNumber = stream.readUTF();
                int age = stream.readInt();
                contacts.add(new Contact(contactName, phoneNumber, age));
                stringsCount--;
            }
        }
        return contacts;
    }

    private static void writeContactSerial(Collection<Contact> contacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contact.bin"))) {
            oos.writeObject(contacts);
            System.out.println("File has been written");
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    private static void readContactSerial() {
        ArrayList<Contact> newContacts = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contact.bin"))) {

            newContacts = ((ArrayList<Contact>) ois.readObject());
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        for (Contact c : newContacts)
            System.out.printf("Name: %s Phone number: %s Age: %d \n", c.getName(), c.getPhoneNumber(), c.getAge());
    }
}
