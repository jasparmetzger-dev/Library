package data;

import model.Book;
import model.Content;


import java.sql.*;
import java.util.List;

public class BookDAO extends GenericDAO<Book, Integer>{

    BookDAO() {
        super(Book.class);
    }

    public int insert(Book b) throws SQLException {
        String sql = """
                INSERT INTO BOOKS (title, author_id, published, content)
                VALUES ( ? , ? , ? , ? , ? )
                """;

        return executeUpdate(sql, b.getId(), b.title, b.getAuthorId(), b.published, b.content);
    }



    protected String getDB_URL() {
        return "jdbc:sqlite:mylibrary.db";
    }
    protected String getTable() {
        return "BOOKS";
    }
    protected List<String> getColumnNames() {
        //              int     str       int          date        str
        return List.of("id", "title", "author_id", "published", "content");
    }

    protected Book mapResults(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("author_id"),
                rs.getDate("published").toString(),
                new Content(rs.getString("content"))
        );
    }
}
