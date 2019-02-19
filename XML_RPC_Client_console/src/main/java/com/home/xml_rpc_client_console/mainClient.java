/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_client_console;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

/**
 *
 * @author vlad
 */
public class mainClient {
    
    private static Logger log = Logger.getLogger(mainClient.class);

    private static void execToServer(String ip, String port, String cmd, String word, Object[] param) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl xrcci = new XmlRpcClientConfigImpl();
        xrcci.setServerURL(new URL(String.format("http://%s:%s", ip, port)));
        log.info(String.format("Подключение к htttp://%s:%s", ip, port));
        xrcci.setConnectionTimeout(60 * 1000);
        xrcci.setReplyTimeout(60 * 1000);

        XmlRpcClient xrc = new XmlRpcClient();
        xrc.setTransportFactory(new XmlRpcCommonsTransportFactory(xrc));
        xrc.setConfig(xrcci);

        Object[] params = null;

        if (cmd.equals("get")) {
            params = new Object[]{ word };
        } else {
            params = new Object[]{ word, param };
        }
        log.info(String.format("Вызов %s", String.format("Handler.%s", cmd)));
        String result = (String) xrc.execute(String.format("Handler.%s", cmd), params);
        log.info(String.format("Результат %s", result));
        System.out.println(result);
    }

    public static void main(String[] args) {
        HelpFormatter hf = new HelpFormatter();
        Options options = new Options();
        options.addOption(Option.builder("h").desc("help").longOpt("help").build());
        options.addOption(Option.builder("i").hasArg().required(true).desc("Server ip address").longOpt("ip").build());
        options.addOption(Option.builder("p").hasArg().required(true).desc("Server port").longOpt("port").build());
        options.addOption(Option.builder("a").hasArgs().desc("Adds the specified meanings of the word to the dictionary, while maintaining the old ones.").longOpt("add").build());
        options.addOption(Option.builder("g").hasArg().desc("Returns the meaning of the word, each word begins on a new line.").longOpt("get").build());
        options.addOption(Option.builder("d").hasArgs().desc("Removes the specified word meanings from the dictionary.").longOpt("delete").build());

        CommandLineParser parser = new DefaultParser();

        String ip = "";
        String port = "";

        try {
            CommandLine cl = parser.parse(options, args);

            ip = cl.getOptionValue("i");
            port = cl.getOptionValue("p");
            String word = "", cmd = "";
            Object[] param = null;

            if (cl.hasOption('h')) {
                hf.printHelp("java -jar mainClient-jar-with-dependencies.jar --help", options);
            }

            if (cl.hasOption("a")) {
                String[] optionValues = cl.getOptionValues('a');
                if (optionValues.length < 2) {
                    System.out.println("No word or meaning is given");
                    return;
                }

                cmd = "add";
                word = optionValues[0];

                ArrayList<String> al = new ArrayList<>();
                for (int i = 1; i < optionValues.length; i++) {
                    al.add(optionValues[i]);
                }
                param = al.toArray();
            }

            if (cl.hasOption("g")) {
                cmd = "get";
                word = cl.getOptionValue('g');
            }

            if (cl.hasOption("d")) {
                String[] optionValues = cl.getOptionValues('d');
                if (optionValues.length < 2) {
                    System.out.println("No word or meaning is given");
                    return;
                }

                cmd = "delete";
                word = optionValues[0];

                ArrayList<String> al = new ArrayList<>();
                for (int i = 1; i < optionValues.length; i++) {
                    al.add(optionValues[i]);
                }
                param = al.toArray();
            }
            
            if(!cmd.equals(""))
                execToServer(ip, port, cmd, word, param);

        } catch (Exception ex) {
            log.error(ex);
            hf.printHelp("java -jar mainClient-jar-with-dependencies.jar --help", options);
        }
    }
}
