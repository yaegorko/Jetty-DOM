package servlets;


import sax.ReadXMLWithSaxParser;
import server.ResourceServerMBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourcesServlet extends HttpServlet {
    private static final String URL = "/resources";
    private final ResourceServerMBean resourceServer;

    public ResourcesServlet(ResourceServerMBean resourceServer) {
        this.resourceServer = resourceServer;
    }

    public static String getURL() {
        return URL;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String path = req.getParameter("path");
       resourceServer.setTestResource(ReadXMLWithSaxParser.readNewResourcesFromXML(path));
    }
}
