
package com.uci.rest.service;

import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.Short;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariqibrahim on 5/28/17.
 */
public class ShortService {


    private final static String ALL_SHORTS_QUERY = "SELECT * FROM short";

    public static Short getShortById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_SHORTS_QUERY + " WHERE id = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Short st = new Short();


                    st.setId(resultSet.getInt("id"));
                    st.setName(resultSet.getString("name"));
                    st.setPrice(resultSet.getInt("price"));
                    st.setColor(resultSet.getString("color"));
                    st.setDescription(resultSet.getString("description"));
                    st.setPic1(resultSet.getString("pic1"));
                    st.setPic2(resultSet.getString("pic2"));
                    st.setPic3(resultSet.getString("pic3"));
                    return st;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;


    }

    public static List<Short> getAllShorts() {
        List<Short> shorts = new ArrayList<Short>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_SHORTS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Short st = new Short();

                    st.setId(resultSet.getInt("id"));
                    st.setName(resultSet.getString("name"));
                    st.setPrice(resultSet.getInt("price"));
                    st.setColor(resultSet.getString("color"));
                    st.setDescription(resultSet.getString("description"));
                    st.setPic1(resultSet.getString("pic1"));
                    st.setPic2(resultSet.getString("pic2"));
                    st.setPic3(resultSet.getString("pic3"));

                    shorts.add(st);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return shorts;
    }

    public static boolean AddShort(Short st) {

        String sql = "INSERT INTO short(id, name, price, color, description, pic1, pic2, pic3)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(st.getId()), st.getName(), String.valueOf(st.getPrice()), st.getColor(), st.getDescription(),
                st.getPic1(), st.getPic2(), st.getPic3());

    }

    public static boolean updateShort(Short st) {

        String sql = "UPDATE short SET name=?, price=?, color=?, description=?, pic1=?, pic2=?, pic3=? WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, st.getName(), String.valueOf(st.getPrice()), st.getColor(), 
                st.getDescription(), st.getPic1(), st.getPic2(), st.getPic3(), String.valueOf(st.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteShort(Short retrievedShort) {

        String sql = "DELETE FROM short WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedShort.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
}
