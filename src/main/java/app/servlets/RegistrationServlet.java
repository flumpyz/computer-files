package app.servlets;

import app.database.DBException;
import app.models.PersonModel;
import app.services.PersonsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    PersonsService personsService;

    public RegistrationServlet(PersonsService personsService) {
        this.personsService = personsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameters = request.getParameterMap();

        if (!parameters.containsKey("login")
                || !parameters.containsKey("password")
                || !parameters.containsKey("email")) {
            request.setAttribute("message", "Invalid input");
            request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
        } else {
            try {
                String login = parameters.get("login")[0];
                String password = parameters.get("password")[0];
                String email = parameters.get("email")[0];

                if (login.length() <= 0 || password.length() <= 0 || email.length() <= 0) {
                    request.setAttribute("message", "Login, password and email fields cannot be empty");
                    request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
                } else {
                    if (personsService.has(new PersonModel(login, password, email))) {
                        response.sendRedirect("http://localhost:8080/login");
                    } else {
                        if (personsService.hasLogin(login)) {
                            request.setAttribute("message", "This login is already registered");
                            request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
                        } else if (personsService.hasEmail(email)) {
                            request.setAttribute("message", "This email is already registered");
                            request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
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
            } catch (SQLException | DBException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
