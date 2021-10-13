package app.servlets;

import app.models.FileModel;
import app.services.FilesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filesServlet", urlPatterns = "/files")
public class MainServlet extends HttpServlet {
    FilesService filesService = new FilesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String formatPath = "";

        if (path != null) {
            formatPath = path;
        } else {
            formatPath = "/";
        }

        req.setAttribute("directory", formatPath);
        Iterable<FileModel> files = filesService.listAllFiles(formatPath);
        req.setAttribute("files", files);
        req.getRequestDispatcher("files.jsp").forward(req, resp);
    }
}
