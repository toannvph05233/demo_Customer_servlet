package services;

import com.mysql.cj.xdevapi.SqlResultImpl;
import dao.ManageCustomer;
import models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {
    public ArrayList<Customer> list = new ArrayList<>();

    public CustomerService(){
        try {
            list = (ArrayList<Customer>) ManageCustomer.seclect();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(Customer customer) throws SQLException {
        ManageCustomer.create(customer);
        list.add(customer);

    }

    public void edit(Customer customer,int index) throws SQLException {
        ManageCustomer.edit(customer);
        list.set(index,customer);
    }

    public void delete(int index) throws SQLException {
        ManageCustomer.delete(list.get(index).getId());
        list.remove(index);
    }

    public ArrayList<Customer> findByName(String name) throws SQLException {
        return ManageCustomer.findByName(name);
    }


}
