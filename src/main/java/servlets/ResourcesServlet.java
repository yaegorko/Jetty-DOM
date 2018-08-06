package servlets;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import server.ResourceServerMBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            System.out.println(document);
            Node root = document.getDocumentElement();

        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}
