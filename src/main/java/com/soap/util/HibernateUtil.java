package com.soap.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;

import io.github.cdimascio.dotenv.Dotenv;

public class HibernateUtil {
    private static final String CONFIG_FILE_TEMPLATE = "/hibernate.cfg.template.xml";
    private static final String CONFIG_FILE = "/hibernate.cfg.xml";
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration conf = new Configuration().configure(makeConfigFile());
            return conf.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static String makeConfigFile() {
        try {        
            File file = new File(CONFIG_FILE);    
            if (file.exists()) return CONFIG_FILE;
            
            // Load .env
            Dotenv dotenv = Dotenv.load();

            // Load XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(CONFIG_FILE_TEMPLATE);
            
            // Set the variables
            doc.getElementsByTagName("property").item(0).setTextContent(dotenv.get("DB_HOST"));
            doc.getElementsByTagName("property").item(1).setTextContent(
                "jdbc:mysql://localhost:3306/" + dotenv.get("DB_NAME")
            );
            doc.getElementsByTagName("property").item(2).setTextContent(dotenv.get("DB_USER"));
            doc.getElementsByTagName("property").item(3).setTextContent(dotenv.get("DB_PASS"));            

            // Save the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(CONFIG_FILE));
    
            return CONFIG_FILE;
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
