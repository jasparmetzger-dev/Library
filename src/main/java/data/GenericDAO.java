package data;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO <T, ID extends Serializable> {

    protected abstract String getDB_URL();
    protected abstract String getTable();
    protected abstract List<String> getColumnNames();
    protected abstract T mapResults(ResultSet rs) throws SQLException;



    private final Class<T> type;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    public  Connection connect() throws SQLException {
        return DriverManager.getConnection(getDB_URL());
    }

    public T getById(ID id) {
        String query = "SELECT * FROM " + getTable() + "WHERE id = " + id;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            T entity = mapResults(rs);
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> getAll() {

        List<T> lst = new ArrayList<>();
        String query = "SELECT * FROM " + getTable();

        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next()) {
                lst.add(mapResults(rs));
            }
            return lst;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
