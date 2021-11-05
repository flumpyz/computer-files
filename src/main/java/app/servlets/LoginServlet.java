package app.servlets;

import app.database.DBException;
import app.models.PersonModel;
import app.services.PersonsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    PersonsService personsService;

    public LoginServlet(PersonsService personsService) {
        this.personsService = personsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("loginPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = session.getId();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.length() <= 0 || password.length() <= 0) {
            if (personsService.hasSession(sessionId)) {
                personsService.removeSession(sessionId);
            }
            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
        } else {
            try {
                if (personsService.has(login, password)) {
                    if (!personsService.hasSession(sessionId)) {
                        personsService.createSession(sessionId, personsService.get(login, password));
                    }

                    String location = "http://localhost:8080/files?path=/";
                    response.sendRedirect(location);
                } else {
                    response.sendRedirect("/registration");
                }
            } catch (SQLException | DBException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
