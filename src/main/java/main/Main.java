package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.TestResource;
import server.ResourceServer;
import server.ResourceServerMBean;
import servlets.ResourcesServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.Servlet;
import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws Exception {

        TestResource testResource = new TestResource();
        ResourceServerMBean resourceServer = new ResourceServer(testResource);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resourceServer, name);

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        Servlet resourceServlet = new ResourcesServlet(resourceServer);
        context.addServlet(new ServletHolder(resourceServlet), ResourcesServlet.getURL());



        server.setHandler(context);
        server.start();

        System.out.println("Server started");
        server.join();

    }
}
