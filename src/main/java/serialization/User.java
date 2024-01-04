package serialization;

import java.io.Serializable;

class User implements Serializable {

    String username;
    String password;

    void sayHello() {
        System.out.println("Hello, " + username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
