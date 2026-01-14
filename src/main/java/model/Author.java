package model;

import java.util.ArrayList;
import java.util.List;

public class Author extends User {

    List<Integer> publishedBookIds;

    public Author(int id,
                  String username,
                  String encodedPassword,
                  List<Integer> readBookIds,
                  List<Integer> publishedBookIds) {
        super(id, username, encodedPassword, readBookIds);
        this.publishedBookIds = publishedBookIds;
    }
    public Author(String username, String password) {
        super(username, password);
        this.publishedBookIds = new ArrayList<>();
    }

    public void publish(Book b) {
        publishedBookIds.add(b.id);
        b.published = Runtime.getRuntime();
        BookDAO.insert(b);
    }
    public Book write ()
}
