package com.example;

import com.example.dao.ContactDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrationresponse")
public class RegistrationResponseServlet extends HttpServlet {
    private ContactDao dao = ContactDao.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("thankyou.jsp").forward(req, resp);

        /*
        Contact contact = (Contact) req.getAttribute("contact");
        req.setAttribute("contact", contact);
        String fn = contact.getFullname();

        PrintWriter pw = resp.getWriter();

        String html = """
                        <html>
                            <body>
                                <p>Thank you $fn for contacting us!</p>
                                <a href="index.jsp">Home</a>
                             </body>
                          </html>
                      """
                .replace("$fn", fn);

        pw.println(html);*/
    }
}
