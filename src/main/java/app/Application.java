package app;

import app.services.PersonsService;
import app.servlets.LoginServlet;
import app.servlets.MainServlet;
import app.servlets.RegistrationServlet;
import com.mysql.jdbc.Driver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        String connectionString = "jdbc:mysql://localhost:3306/?user=root&password=admin1234";
        Connection connection = null;
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonsService personsService = new PersonsService(connection);

        context.addServlet("files", new MainServlet(personsService)).addMapping("/files");
        context.addServlet("login", new LoginServlet(personsService)).addMapping("/login");
        context.addServlet("registration", new RegistrationServlet(personsService)).addMapping("/registration");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
