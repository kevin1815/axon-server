package com.shuyun.loyalty.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetResearch {

    public static void main(String[] args) throws Exception {}

    static void research(String sql, DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.createStatement();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            // resultSet.previous();
        }
    }

}
