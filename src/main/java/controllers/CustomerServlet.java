package controllers;

import dao.ConnectMySql;
import dao.SelectCustomer;
import models.Customer;
import services.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                resp.sendRedirect("views/createCustomer.jsp");
                break;
            case "edit":
                int indexEdit = Integer.parseInt(req.getParameter("index"));
                req.setAttribute("customer",customerService.list.get(indexEdit));
                requestDispatcher = req.getRequestDispatcher("views/editCustomer.jsp");
                requestDispatcher.forward(req,resp);
                break;

            case "delete":
                int index = Integer.parseInt(req.getParameter("index"));
                customerService.delete(index);
                resp.sendRedirect("/customer");
                break;

            case "find":

            default:
                req.setAttribute("listCustomer",customerService.list);
                requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                requestDispatcher.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        switch (action){
            case "create":
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String address = req.getParameter("address");

                Customer customer = new Customer(id,name,email,address);
                customerService.save(customer);

                // chuyển hướng request và response sang thàng jsp
                req.setAttribute("listCustomer",customerService.list);
                requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                requestDispatcher.forward(req,resp);
                break;

            case "edit":
                int idedit = Integer.parseInt(req.getParameter("id"));
                String nameedit = req.getParameter("name");
                String emailedit = req.getParameter("email");
                String addressedit = req.getParameter("address");

                Customer customerEdit = new Customer(idedit,nameedit,emailedit,addressedit);

                int index = Integer.parseInt(req.getParameter("index"));
                customerService.edit(customerEdit,index);

                // chuyển hướng request và response sang thàng jsp
                req.setAttribute("listCustomer",customerService.list);
                requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                requestDispatcher.forward(req,resp);

                break;
        }
    }
}
