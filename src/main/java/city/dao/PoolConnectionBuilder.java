package city.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder{
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
}
