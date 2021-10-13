package app;

import app.services.PersonsService;
import app.servlets.LoginServlet;
import app.servlets.MainServlet;
import app.servlets.RegistrationServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PersonsService personsService = new PersonsService();
        ServletContext context = sce.getServletContext();
        context.addServlet("files", new MainServlet(personsService)).addMapping("/files");
        context.addServlet("login", new LoginServlet(personsService)).addMapping("/login");
        context.addServlet("registration", new RegistrationServlet(personsService)).addMapping("/registration");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
