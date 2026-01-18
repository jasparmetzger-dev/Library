package data;

import model.User;
import util.Conversions;

import java.sql.*;
import java.util.List;

public class UserDAO extends GenericDAO<User, Integer>{

    public UserDAO() {
        super(User.class);
    }


    public int insert(User u) throws SQLException {
        String sql = """
                INSERT INTO USERS (username, password, read_books)
                VALUES ( ? , ? , ? )
                """;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setString(1, u.username);
            stmt.setString(2, u.getEncodedPassword());
            stmt.setString(3, Conversions.makeIntLstStr(u.getReadBooks()));

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(User u) throws SQLException {
        String sql = """
                UPDATE USERS SET
                username = ? ,
                password = ? ,
                read_books = ?
                WHERE id = ?
                """;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             )
        {
            stmt.setString(1, u.username);
            stmt.setString(2, u.getEncodedPassword());
            stmt.setString(3, Conversions.makeIntLstStr(u.getReadBooks()));
            stmt.setInt(4, u.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected String getDB_URL() {
        return "jdbc:sqlite:mylibrary.db";
    }
    protected String getTable() {
        return "USERS";
    }
    protected List<String> getColumnNames() {
        //              int     str         str         str (id1,id2...)
        return List.of("id", "username", "password", "read_books");
    }

    protected User mapResults(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                Conversions.makeStrIntLst(rs.getString("read_books"))
        );
    }

}
