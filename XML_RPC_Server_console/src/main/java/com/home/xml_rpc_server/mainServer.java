/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_server;

import java.io.File;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author vlad
 */
public class mainServer {
    private static WebServer ws;
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to start XML-RPC Server...");
            ws = new WebServer(8095);

            XmlRpcServer xrs = ws.getXmlRpcServer();
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("Handler", Handler.class);
            xrs.setHandlerMapping(phm);
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xrs.getConfig();
            serverConfig.setContentLengthOptional(false);

            ws.start();
            System.out.println("Registered Handler class ");
            System.out.println("Now accepting requests...");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        ws.shutdown();
        System.out.println("Server shutdown...");
    }
}
