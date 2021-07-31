package controllers;

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

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                resp.sendRedirect("views/createCustomer.jsp");
                break;
            case "edit":
                int indexEdit = Integer.parseInt(req.getParameter("index"));
                req.setAttribute("customer", customerService.list.get(indexEdit));
                requestDispatcher = req.getRequestDispatcher("views/editCustomer.jsp");
                requestDispatcher.forward(req, resp);
                break;

            case "delete":
                int index = Integer.parseInt(req.getParameter("index"));
                try {
                    customerService.delete(index);
                    resp.sendRedirect("/customer");
                } catch (Exception e) {
                    resp.sendRedirect("views/404.jsp");
                }
                break;

            case "findName":
                String name = req.getParameter("findName");
                try {
                    req.setAttribute("listCustomer", customerService.findByName(name));
                    requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                    requestDispatcher.forward(req, resp);
                } catch (Exception e) {
                    resp.sendRedirect("views/404.jsp");
                }
                break;

            default:
                req.setAttribute("listCustomer", customerService.list);
                requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "create":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    String name = req.getParameter("name");
                    String email = req.getParameter("email");
                    String address = req.getParameter("address");

                    Customer customer = new Customer(id, name, email, address);
                    customerService.save(customer);
                    req.setAttribute("listCustomer", customerService.list);
                    requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                    requestDispatcher.forward(req, resp);

                } catch (Exception e) {
                    resp.sendRedirect("views/404.jsp");
                }

                break;

            case "edit":
                try {
                    int idedit = Integer.parseInt(req.getParameter("id"));
                    String nameedit = req.getParameter("name");
                    String emailedit = req.getParameter("email");
                    String addressedit = req.getParameter("address");

                    Customer customerEdit = new Customer(idedit, nameedit, emailedit, addressedit);

                    int index = Integer.parseInt(req.getParameter("index"));
                    customerService.edit(customerEdit, index);
                    // chuyển hướng request và response sang thàng jsp
                    req.setAttribute("listCustomer", customerService.list);
                    requestDispatcher = req.getRequestDispatcher("views/ShowCustomer.jsp");
                    requestDispatcher.forward(req, resp);
                } catch (Exception e) {
                    resp.sendRedirect("views/404.jsp");
                }
                break;
        }
    }
}
