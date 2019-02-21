
package com.uci.rest.service;

import com.uci.rest.db.DatabaseConnector;
import com.uci.rest.db.DatabaseUtils;
import com.uci.rest.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tariqibrahim on 5/28/17.
 */
public class OrderService {


    private final static String ALL_ORDERS_QUERY = "SELECT * FROM `order`";

    public static Order getOrderById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY + " WHERE id = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Order od = new Order();

                    od.setId(resultSet.getInt("id"));
                    od.setShortId(resultSet.getInt("shortId"));
                    od.setSize(resultSet.getInt("size"));
                    od.setQty(resultSet.getInt("qty"));
                    od.setCustomerId(resultSet.getInt("customerId"));
                    od.setAddress(resultSet.getString("address"));
                    od.setShipMethod(resultSet.getString("shipMethod"));
                    od.setCity(resultSet.getString("city"));
                    od.setState(resultSet.getString("state"));
                    od.setZip(resultSet.getString("zip"));
                    return od;

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

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Order od = new Order();

                    od.setId(resultSet.getInt("id"));
                    od.setShortId(resultSet.getInt("shortId"));
                    od.setSize(resultSet.getInt("size"));
                    od.setQty(resultSet.getInt("qty"));
                    od.setCustomerId(resultSet.getInt("customerId"));
                    od.setAddress(resultSet.getString("address"));
                    od.setShipMethod(resultSet.getString("shipMethod"));
                    od.setCity(resultSet.getString("city"));
                    od.setState(resultSet.getString("state"));
                    od.setZip(resultSet.getString("zip"));

                    orders.add(od);

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

        return orders;
    }

    public static boolean AddOrder(Order od) {

        String sql = "INSERT INTO `order`(id, shortId, size, qty, customerId, address, shipMethod, city, state, zip)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(od.getId()), String.valueOf(od.getShortId()),
                String.valueOf(od.getSize()), String.valueOf(od.getQty()),String.valueOf(od.getCustomerId()),
                od.getAddress(), od.getShipMethod(), od.getCity(), od.getState(), od.getZip());

    }

    public static boolean updateOrder(Order od) {

        String sql = "UPDATE `order` SET shortId=?, size=?, qty=?, customerId=?, address=?, shipMethod=?, city=? , state=?, zip=? WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(od.getShortId()),
                String.valueOf(od.getSize()), String.valueOf(od.getQty()),String.valueOf(od.getCustomerId()),
                od.getAddress(), od.getShipMethod(), od.getCity(), od.getState(), od.getZip(), String.valueOf(od.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteOrder(Order retrievedOrder) {

        String sql = "DELETE FROM `order` WHERE id=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(retrievedOrder.getId()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
}
