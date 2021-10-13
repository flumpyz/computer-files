package app.servlets;

import app.models.PersonModel;
import app.services.PersonsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    PersonsService personsService;

    public RegistrationServlet(PersonsService personsService) {
        this.personsService = personsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (login == null || password == null || email == null) {
            request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
        } else {
            if (personsService.has(new PersonModel(login, password, email))) {
                response.sendRedirect("http://localhost:8080/login");
            } else {
                HttpSession session = request.getSession();
                String key = session.getId();

                PersonModel person = personsService.create(login, password, email);
                personsService.createSession(key, person);

                String location = "http://localhost:8080/files?path=/";
                response.sendRedirect(location);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
