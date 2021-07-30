package services;

import dao.SelectCustomer;
import models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {
    public ArrayList<Customer> list = new ArrayList<>();

    public CustomerService(){
        try {
            list = (ArrayList<Customer>) SelectCustomer.seclect();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(Customer customer){
        list.add(customer);
    }

    public void edit(Customer customer,int index){
        list.set(index,customer);
    }

    public void delete(int index){
        list.remove(index);
    }

    public Customer findById(int id){
        for (Customer c:list) {
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }


}
