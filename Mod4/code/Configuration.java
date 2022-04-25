/**
 * XML parser for configuration parameters.
 *
 * You can use this file with your web server.
 * 
 * This maps configuration parameters to a HashMap and are retrieved
 * through the following getter methods:
 *
 * 	public String getLogFile() 
 * 	public String getDocBase() 
 * 	public String getServerName() 
 *
 * Usage:
 * 	Configuration config = new Configuration(<XML configuration file>);
 *
 *	config.getLogFile();
 *	config.getDocBase();
 *	config.getServerName();
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import java.util.Map;
import java.util.HashMap;

public class Configuration extends DefaultHandler
{
    private Map map;
    private String configurationFile;

    /* Read/Write data to configuration file  */ 
    public Configuration(String configurationFile) throws ConfigurationException {
	this.configurationFile = configurationFile;

	map = new HashMap();

	try {        
        	// Use the default (non-validating) parser
        	SAXParserFactory factory = SAXParserFactory.newInstance();

        	// Parse the input
        	SAXParser saxParser = factory.newSAXParser();
        	saxParser.parse( new File(configurationFile), this);
	}
	catch (javax.xml.parsers.ParserConfigurationException pce) {
		throw new ConfigurationException("javax.xml.parsers.ParserConfigurationException");
	}
	catch (org.xml.sax.SAXException se) {
		throw new ConfigurationException("org.xml.sax.SAXException");
	}
	catch (java.io.IOException ioe) {
		throw new ConfigurationException("java.io.IOException");
	}
    }


	/* Element Start Notifcation */
    public void startElement(String namespaceURI,
                             String lName, 	
                             String qName, 	
                             Attributes attrs)	
    throws SAXException
    {
        String elementName = lName; // element name
        if ("".equals(elementName)) 
		elementName = qName; // namespaceAware = false

	/* If atribute is not null get name */
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                String aName = attrs.getLocalName(i); // Attr name 
                if ("".equals(aName)) 
			aName = attrs.getQName(i);

		/* instert attribuite name into map */
		map.put(elementName+"."+aName,attrs.getValue(i));
            }
        }
    }

	/* initiate getLogFile */
	public String getLogFile() {
		return (String)map.get("logfile.log");
	}

	/* initiate getDocBase*/
	public String getDocBase() {
		return (String)map.get("context.docBase");
	}

	/* initiate getServerName*/
	public String getServerName() {
		return (String)map.get("webserver.title");
	}
}
