/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_server;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author vlad
 */
public class mainServer {

    private static Logger log = Logger.getLogger(mainServer.class);

    private static WebServer ws;

    private static void updateLog4jConfiguration() {
        Properties props = new Properties();
        try (InputStream configStream = mainServer.class.getResourceAsStream("/default.properties")) {
            props.load(configStream);
        } catch (Exception e) {
            System.out.println("Errornot laod configuration file ");
        }
        PropertyConfigurator.configure(props);
    }

    public static void main(String[] args) {

        URL propertiesUrl = mainServer.class.getResource("/log4j.properties");
        if (propertiesUrl == null) {
            //Hide no appender warning
            Logger.getRootLogger().setLevel(Level.OFF);
            log.info("Load default logger properties");
            updateLog4jConfiguration();
            log.info("Default logger properties are loaded");
        }

        try {
            System.out.println("Attempting to start XML-RPC Server on 8095 port...");
            log.info("Attempting to start XML-RPC Server on 8095 port...");
            ws = new WebServer(8095);

            XmlRpcServer xrs = ws.getXmlRpcServer();
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("Handler", Handler.class);
            xrs.setHandlerMapping(phm);
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xrs.getConfig();
            serverConfig.setContentLengthOptional(false);

            ws.start();
            System.out.println("Registered Handler class");
            log.info("Registered Handler class");
            System.out.println("Now accepting requests...");
            log.info("Now accepting requests...");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        ws.shutdown();
        System.out.println("Server shutdown...");
        log.info("Server shutdown...");
    }
}
