/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_client_console;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

/**
 *
 * @author vlad
 */
public class mainClient {

    public static void main(String[] args) {
        try {
            XmlRpcClientConfigImpl xrcci = new XmlRpcClientConfigImpl();
            xrcci.setServerURL(new URL("http://localhost:8095/"));
            xrcci.setConnectionTimeout(60 * 1000);
            xrcci.setReplyTimeout(60 * 1000);

            XmlRpcClient xrc = new XmlRpcClient();
            xrc.setTransportFactory(new XmlRpcCommonsTransportFactory(xrc));
            xrc.setConfig(xrcci);
            

            Object[] params = new Object[]
              { new String("man"), new String("uiyuiyotv") };
            
            Object[] paramsS = new Object[]
              { new String("man") };
            
//            Object result = (Object)xrc.execute("Handler.add", params);
            Object result = (Object)xrc.execute("Handler.get", paramsS);
            System.out.println("say: " + result);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
