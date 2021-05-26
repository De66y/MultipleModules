package com.example;

import com.example.dao.ContactDao;
import com.example.domain.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register") //Nu hoef ik niks meer in mijn web.xml te zetten
public class RegistrationServlet extends HttpServlet {
    private ContactDao contactDao = ContactDao.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);

        Contact contact = new Contact(firstname, lastname, email);
        contactDao.add(contact);

        req.getSession().setAttribute("email", contact.getEmail()); //Onder deze naam (email), zet ik deze waarde

        req.setAttribute("contact", contact); //Onder deze naam (contact), zet ik deze waarde
        req.getRequestDispatcher("/registrationresponse").forward(req, resp);

        // or
        // resp.sendRedirect("registrationresponse.html");

        // or
        // resp.sendRedirect("registrationresponse?email=" + contact.getEmail());

    }

}
