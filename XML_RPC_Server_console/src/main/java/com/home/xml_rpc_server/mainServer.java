/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_server;

import java.io.File;
import org.apache.log4j.Logger;
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
    public static void main(String[] args) {
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
