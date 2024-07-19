package bla;

import java.io.*;
import java.util.Scanner;

public class Abc {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter person name : ");
        String name = input.nextLine();
        System.out.println("Enter person age : ");
        int age = input.nextInt();
        System.out.println("Enter person address : ");
        String address = input.nextLine();
        System.out.println("Enter person tel : ");
        int tel = input.nextInt();

        Person person=new Person(name,age,address,tel);

        serialization(person);

        Person deSerializedPerson=deSerialization();

        System.out.println(deSerializedPerson);

    }

    private static Person deSerialization() {
        Person person=null;
        try {
            FileInputStream fileInputStream=new FileInputStream("person.ser");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            person=(Person) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("deSerialVersionUid :"+ ObjectStreamClass.lookup(Person.class).getSerialVersionUID());
        System.out.println("\n=========================================\n");
        return person;
    }

    private static void serialization(Person person) {
        System.out.println("person name : "+person.getName());
        System.out.println("person age : "+person.getAge());
        System.out.println("person address : "+person.getAddress());
        System.out.println("person tel : "+person.getTel());
        System.out.println("\n=========================================\n");

        try {
            FileOutputStream outputStream=new FileOutputStream("person.ser");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
            outputStream.close();
            System.out.println("serialized person successfully");
            System.out.println("serialVersionUid :"+ ObjectStreamClass.lookup(Person.class)
                    .getSerialVersionUID());
            System.out.println("\n=========================================\n");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
 }


}

}
