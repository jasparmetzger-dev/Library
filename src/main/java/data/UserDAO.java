package data;

import model.User;
import util.Conversions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String readBookString = Conversions.makeIntLstStr(u.getReadBooks());

        return executeUpdate(sql, u.username, u.getEncodedPassword(), readBookString);
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
