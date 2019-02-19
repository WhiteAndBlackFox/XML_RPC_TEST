/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_server;

import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author vlad
 */
public class Handler {

    private static Logger log = Logger.getLogger(mainServer.class);

    private Storage storage = Storage.getInstance();

    public String add(String name, Object[] vals) {
        try {
            for (Object val : vals) {
                Integer id = storage.getId(name);
                String val_s = String.valueOf(val);
                storage.putVal(id, name, val_s);
            }
            return "значение(я) слова успешно добавлены";
        } catch (Exception ex) {
            return "значение(я) слова не добавлены";
        }
    }

    public String get(String name) {
        String val = storage.getVal(name);
        return val;
    }

    public String delete(String name, Object[] vals) {
        try {
            for (Object val : vals) {
                String val_s = String.valueOf(val);
                if (storage.removeVal(storage.getId(name), val_s)) {
                    return "значение(я) слова успешно удален";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex);
        }
        return "слово/значение(я) отсутвует в словар";
    }

//    public String sayHello(String name, ArrayList<String> vals) {
//        return "Hello " + name;
//    }
}
