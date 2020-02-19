package Data;

import Logic.ProductListManager;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class User  implements Serializable, Comparable {

    public static final long serialVersionUID = 13452345255L;
    public final String username;
    public int age;
    public int height;
    public int weight;
    public ProductListManager plm;
    public static HashMap<String, User> createdUsers = new HashMap<>();
    public static ArrayList<String> usernames = new ArrayList<>();
    public static final Path usernamesPath = new File("UserData\\usernames.ser").toPath();

    public User (String username, int age, int height, int weight){
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
        plm = new ProductListManager(this);
        createdUsers.put(username, this);
        usernames.add(username);
        saveUser();
    }

    public User (String username, int age, int height, int weight, ProductListManager plm){
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.plm = plm;
        if (!createdUsers.containsValue(this)) {  createdUsers.put(username, this); }
        if (!usernames.contains(username)) { usernames.add(username);}
        saveUser();
    }

    public void saveUser() {
        try {
            final FileOutputStream fileOut = new FileOutputStream(usernamesPath.toFile());
            final ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(usernames);
            System.out.println("Usernames saved");
            objectOut.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.out.println("Unable to save the usernames");
        }

        try {
            final FileOutputStream fileOut = new FileOutputStream(new File("UserData\\" + this.username + ".ser"));
            final ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            System.out.println(this.username + " saved");
            objectOut.close();
            fileOut.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to save the users");
        }
    }

    public static void loadUsers() {
        System.out.println("loading users");
        try {
            final FileInputStream fileIn = new FileInputStream(usernamesPath.toFile());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            usernames = (ArrayList<String>)objectIn.readObject();
            System.out.println("Usernames loaded");
            objectIn.close();
            fileIn.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to load the usernames");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            for (String username : usernames) {
                final FileInputStream fileIn = new FileInputStream(new File("UserData\\" + username + ".ser"));
                final ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                User u = (User) objectIn.readObject();
                createdUsers.put(u.username, u);
                fileIn.close();
                objectIn.close();
                System.out.println(username + " retrieved");
            }
        }
        catch (IOException e) {
            System.out.println(createdUsers.size() + " users retrieved");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String introduceMe (){
        return "Hi, I'm" + username + ", I'm " + age + " years old, weight " + weight + ", and I'm " + height + " centimeters tall";
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
