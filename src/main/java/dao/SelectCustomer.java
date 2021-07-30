package dao;

import models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectCustomer {
    static String select = "select * from customer";

    public static List<Customer> seclect() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> list = new ArrayList<>();
        // tạo connect đến CSDL
        Connection connection = ConnectMySql.getConnect();
        // tạo ra Statement bằng connection.
        Statement statement = connection.createStatement();
        // thực câu truy vấn bằng statement.executeQuery và kết quả trả về là ResultSet;
        ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email =resultSet.getString("email");
            String address =resultSet.getString("address");

            list.add(new Customer(id,name,email,address));
        }
        return list;
    }
}
