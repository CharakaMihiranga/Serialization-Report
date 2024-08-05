import dto.User;

import java.io.*;
import java.util.Scanner;

public class Main {

    //Serialization = 	The process of converting an object into a byte stream.
    //					Persists (saves the state) the object after program exits
    //					This byte stream can be saved as a file or sent over a network

    //					Byte stream can be saved as a file (.ser) which is platform independent


    //					Steps to Serialize
    //					---------------------------------------------------------------
    //					1. Your class should implement Serializable interface
    //					2. add import java.io.Serializable;
    //					3. FileOutputStream fileOut = new FileOutputStream(file path)
    //					4. ObjectOutputStream out = new ObjectOutputStream(fileOut);
    //					5. out.writeObject(objectName)
    //					6. out.close(); fileOut.close();
    //					------------------------------------------------------------

    //Deserialization = 	The reverse process of converting a byte stream into an object.
    //					(Think of this as if you're loading a saved file)

    //					Steps to Deserialize
    //					---------------------------------------------------------------
    //					1. Your class should implement Serializable interface
    //					2. add import java.io.Serializable;
    //					3. FileInputStream fileIn = new FileInputStream(file path);
    //					4. ObjectInputStream in = new ObjectInputStream(fileIn);
    //					5. objectNam = (Class) in.readObject();
    //					6. in.close(); fileIn.close();
    //					---------------------------------------------------------------

    // important notes	1. children classes of a parent class that implements Serializable will do so as well
    //					2. static fields are not serialized (they belong to the class, not an individual object)
    //					3. Fields declared as "transient" aren't serialized, they're ignored
    //					4. the class's definition ("class file") itself is not recorded, cast it as the object type
    //					5. serialVersionUID is a unique version ID

    //SerialVersionUID =	serialVersionUID is a unique ID that functions like a version #
    //					verifies that the sender and receiver of a serialized object,
    //					have loaded classes for that object that are compatible
    //					Ensures object will be compatible between machines
    //					Number must match. otherwise this will cause a InvalidClassException
    //					A SerialVersionUID will be calculated based on class properties, members, etc.
    //					A serializable class can declare its own serialVersionUID explicitly (recommended)



    public static void serialization(User user) {
        System.out.println("\n=========================================\n");
        System.out.println(
                "========User========\n"+
                        "Username: "+ user.getName()+"\n"+
                        "Email: "+ user.getEmail()+"\n"+
                        "Password: "+ user.getPassword()+"\n"
        );
        try {
            FileOutputStream fileOut = new FileOutputStream("user.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(user);
            out.close();
            fileOut.close();

            System.out.println("Serialized data is saved in user.ser\n\n");

        } catch (IOException i) {
            i.printStackTrace();
        }

        long serialVersionUID = ObjectStreamClass.lookup(User.class).getSerialVersionUID();
        System.out.println("SerialVersionUID in serialization method: " + serialVersionUID);

        System.out.println("\n=========================================\n");

    }
    public static User deserialization() {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream("user.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            user = (User) in.readObject(); //programmer must cast the object to the original class

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            c.printStackTrace();
        }
        long serialVersionUID = ObjectStreamClass.lookup(User.class).getSerialVersionUID();
        System.out.println("SerialVersionUID in deserialization method: " + serialVersionUID)
        System.out.println("\n=========================================\n");
        return user;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter user email:");
        String email = scanner.nextLine();
        System.out.println("Enter user password:");
        String password = scanner.nextLine();

        User user = new User(
                name,
                email,
                password
        );

        serialization(user);
        User deserializedUser = deserialization();
        System.out.println(
                "========Deserialized User========\n"+
                "Username: "+ deserializedUser.getName()+"\n"+
                "Email: "+ deserializedUser.getEmail()+"\n"+
                "Password: "+ deserializedUser.getPassword()
        );
    }
}
