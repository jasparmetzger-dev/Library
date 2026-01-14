package data;

import model.User;
import util.Conversions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends GenericDAO{

    public UserDAO(Class type) {
        super(type);
    }


    public int insert(User u) throws SQLException {
        String sql = """
                INSERT INTO USERS (id, username, password, read_books)
                VALUES ( ? , ? , ? , ? )
                """;
        String readBookString = Conversions.makeIntLstStr(u.getReadBooks());

        return executeUpdate(sql, u.getId(), u.username, u.getEncodedPassword(), readBookString);
    }
    public int delete(User u) throws SQLException {
        String sql = "DELETE FROM USERS WHERE id = " + u.getId();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement())
        {
            return stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    protected String getDB_URL() {
        return "jdbc:sqlite:mylibrary.db";
    }
    protected String getTable() {
        return "USERS";
    }
    protected List<String> getColumnNames() {
        List<String> cols = new ArrayList<>();
        cols.add("id"); //int
        cols.add("username"); //str
        cols.add("password"); //str
        cols.add("read_books"); //str
        return cols;
    }

    protected Object mapResults(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                Conversions.makeStrIntLst(rs.getString("read_books"))
        );
    }


}
