package serialization;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        User user = new User();
        user.username = "saronistu98";
        user.password = "sandrabullock";
        user.sayHello();

        FileOutputStream fileOutputStream = new FileOutputStream("user-info.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("User info saved");

        FileInputStream fileInputStream = new FileInputStream("user-info.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User deserializedUser = (User) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        System.out.println(deserializedUser);
    }
}
