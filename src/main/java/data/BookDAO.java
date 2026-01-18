package data;

import model.Book;
import model.Content;
import model.User;
import util.Conversions;


import java.sql.*;
import java.util.List;

public class BookDAO extends GenericDAO<Book, Integer>{

    BookDAO() {
        super(Book.class);
    }

    public int insert(Book b) throws SQLException {
        String sql = """
                INSERT INTO BOOKS (title, author_id, published, content, status)
                VALUES ( ? , ? , ? , ? , ? )
                """;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, b.title);
            stmt.setInt(2, b.getAuthorId());
            stmt.setDate(3, Date.valueOf(b.published));
            stmt.setString(4, b.getContent());
            stmt.setString(5, Conversions.makeStatusString(b.getStatus()));

            return stmt.executeUpdate();

        }
    }
    public int update(Book b) throws SQLException {
        String sql = """
                UPDATE BOOKS SET
                title = ? ,
                author_id = ? ,
                published = ? ,
                content = ? ,
                status = ?,
                WHERE id = ?
                """;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setString(1, b.title);
            stmt.setInt(2, b.getAuthorId());
            stmt.setDate(3, Date.valueOf(b.published));
            stmt.setString(4, b.getContent());
            stmt.setString(5, Conversions.makeStatusString(b.getStatus()));
            stmt.setInt(6, b.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    protected String getDB_URL() {
        return "jdbc:sqlite:mylibrary.db";
    }
    protected String getTable() {
        return "BOOKS";
    }
    protected List<String> getColumnNames() {
        //              int     str       int          date        str      str
        return List.of("id", "title", "author_id", "published", "content", "status");
    }

    protected Book mapResults(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("author_id"),
                rs.getDate("published").toString(),
                new Content(rs.getString("content")),
                Conversions.makeStatus(rs.getString("status"))
        );
    }
}
