package app;

import app.database.DatabaseService;
import app.database.entities.User;
import app.services.PersonsService;
import app.servlets.LoginServlet;
import app.servlets.MainServlet;
import app.servlets.RegistrationServlet;
import com.mysql.jdbc.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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

        DatabaseService databaseService = new DatabaseService();

        PersonsService personsService = new PersonsService(databaseService);

        context.addServlet("files", new MainServlet(personsService)).addMapping("/files");
        context.addServlet("login", new LoginServlet(personsService)).addMapping("/login");
        context.addServlet("registration", new RegistrationServlet(personsService)).addMapping("/registration");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
