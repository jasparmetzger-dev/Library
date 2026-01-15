package model;

public class Book extends GenericEntity{
    private int id;
    public String title;
    private final int authorId;
    public String published;
    public Content content;

    public Book(String title, int author, Content content) {
        this.title = title;
        this.authorId = author;
        this.content = content;
    }
    public Book(int id, String title, int author,String published, Content content) {
        this.id = id;
        this.title = title;
        this.authorId = author;
        this.published = published;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }
    public int getAuthorId() {
        return this.authorId;
    }
}
