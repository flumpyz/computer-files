package app.servlets;

import app.models.FileModel;
import app.services.FilesService;
import app.services.PersonsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    FilesService filesService = new FilesService();
    PersonsService personsService;
    private final String rootDirectory = "/";

    public MainServlet(PersonsService personsService) {
        this.personsService = personsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String key = session.getId();

        if (personsService.hasSession(key)) {
            String login = personsService.getLoginBySessionKey(key);
            String path = req.getParameter("path");
            String formatPath = "";

            if (path != null) {
                formatPath = rootDirectory + login + path;
            } else {
                formatPath = rootDirectory + login;
            }

            req.setAttribute("directory", formatPath);
            Iterable<FileModel> files = filesService.listAllFiles(formatPath);
            req.setAttribute("files", files);
            req.getRequestDispatcher("files.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("http://localhost:8080/login");
        }
    }
}
