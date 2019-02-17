/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_server;

import java.util.ArrayList;

/**
 *
 * @author vlad
 */
public class Handler {

    private Storage storage = Storage.getInstance();

    public String add(String name, String val) {
        storage.putVal(storage.getId(name), name, val);
        return name;
    }

    public String get(String name) {
        String val = storage.getVal(name);
        return val;
    }

    public String sayHello(String name, ArrayList<String> vals) {
        return "Hello " + name;
    }

}
