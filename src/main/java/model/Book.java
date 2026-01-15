package model;

public class Book {
    int id;
    String title;
    int authorId;
    Runtime published;
    Content content;

    public Book(String title, int author, Content content) {
        this.title = title;
        this.authorId = author;
        this.content = content;
    }
    public Book(int id, String title, int author, Content content) {
        this.id = id;
        this.title = title;
        this.authorId = author;
        this.published = published;
        this.content = content;
    }
}
