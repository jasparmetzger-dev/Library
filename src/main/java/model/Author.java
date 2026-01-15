package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Author extends User {

    List<Integer> publishedBookIds;

    public Author(String username, String password) {
        super(username, password);
        this.publishedBookIds = new ArrayList<>();
    }
    public Author(int id,
                  String username,
                  String encodedPassword,
                  List<Integer> readBookIds,
                  List<Integer> publishedBookIds) {
        super(id, username, encodedPassword, readBookIds);
        this.publishedBookIds = publishedBookIds;
    }

    public void publish(Book b) {
        publishedBookIds.add(b.getId());
        b.published = LocalDate.now().toString();
    }
    public Book write (String title, String text) {
        return new Book(title, this.getId(), new Content(text));
    }

}
