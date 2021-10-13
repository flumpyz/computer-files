package app.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "downloadServlet", value = "/download")
public class DownloadServlet extends HttpServlet {
    private static final int BufferSize = 2048;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        String[] pathElements = path.split("/");
        String fileName = pathElements[pathElements.length - 1];

        response.setContentType("text/plain");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));

        try (InputStream inputStream = new FileInputStream(path);
             OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[BufferSize];

            int numBytesRead;
            while ((numBytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, numBytesRead);
            }
        }
    }
}
