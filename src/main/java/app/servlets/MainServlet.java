package app.servlets;

import app.models.FileModel;
import app.services.FilesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    FilesService filesService = new FilesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path root = Paths.get(System.getProperty("user.dir")).getFileSystem()
                .getRootDirectories().iterator().next();
        req.setAttribute("directory", root);
        Iterable<FileModel> files = filesService.listAllFiles(String.valueOf(root));
        req.setAttribute("files", files);
        req.getRequestDispatcher("files.jsp").forward(req, resp);
    }
}
