package dao;

import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageCustomer {
    static Connection connection;
    static ArrayList<Customer> list = new ArrayList<>();

    static {
        try {
            connection = ConnectMySql.getConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static List<Customer> seclect() throws SQLException, ClassNotFoundException {
        String select = "select * from customer";
        // tạo ra Statement bằng connection.
        Statement statement = connection.createStatement();
        // thực câu truy vấn bằng statement.executeQuery và kết quả trả về là ResultSet;
        ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");

            list.add(new Customer(id, name, email, address));
        }
        return list;
    }

    public static void create(Customer customer) throws SQLException {
        String create = "insert into customer value(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(create);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.execute();
    }

    public static void edit(Customer customer) throws SQLException {
        String edit = "update customer set name=?,email=?,address=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(edit);
        preparedStatement.setInt(4, customer.getId());
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.execute();
    }

    public static void delete(int id) throws SQLException {
        String edit = "delete from customer where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(edit);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public static ArrayList<Customer> findByName(String findName) throws SQLException {
        ArrayList<Customer> findList = new ArrayList<>();
        String edit = "select * from customer where name like '%" + findName +"%'";
        PreparedStatement preparedStatement = connection.prepareStatement(edit);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");

            findList.add(new Customer(id, name, email, address));
        }
        return findList;

    }


}
