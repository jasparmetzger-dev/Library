package model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class User extends GenericEntity {
    private int id;
    public String username;
    protected String encodedPassword;
    List<Integer> readBookIds;


    User(String username, String decodedPassword) {
        this.username = username;
        this.encodedPassword = encodePassword(decodedPassword);
        this.readBookIds = new ArrayList<>();
    }

    public User(int id, String username, String encodedPassword, List<Integer> readBookIds) {
        this.id = id;
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.readBookIds = readBookIds;
    }

    public void read(Book b) {
        //consume knowledge
    }



    public int getId() {return this.id;}
    public String getEncodedPassword() {return this.encodedPassword;}
    public List<Integer> getReadBooks() {return this.readBookIds;}


    private String encodePassword(String decodedPassword) {
        return Base64.getEncoder().encodeToString(decodedPassword.getBytes(StandardCharsets.UTF_8));
    }
    private String decodePassword(String encodedPassword) {
        return new String (Base64.getDecoder().decode(encodedPassword));
    }

}
