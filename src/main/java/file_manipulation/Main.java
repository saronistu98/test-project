package file_manipulation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader("src/main/resources/secret_message.txt");
            int data = fileReader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = fileReader.read();
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter writer = new FileWriter("src/main/resources/secret_message.txt");
            writer.write("Saron o iubeste pe Patri");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
