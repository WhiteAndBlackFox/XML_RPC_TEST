/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.xml_rpc_server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

/**
 *
 * @author vlad
 */
public class Storage {

    private static Logger log = Logger.getLogger(mainServer.class);

    private static Storage instance = null;
    private List<jlists> mjlists = new LinkedList<jlists>();

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Storage() {
    }

    public synchronized Integer getId(String name) {
        int id = -1;
        try {
            id = mjlists.stream().map((m) -> m.getName()).collect(Collectors.toList()).indexOf(name);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex);
        }
        return id;
    }

    public synchronized String getList() {
        String val = "";
        try {
            val = mjlists.stream().map((m) -> m.getName()).collect(Collectors.joining("\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex);
        }
        if (!val.equals(""))
            return val;
        else
            return "Список пустой";
    }

    public synchronized void putVal(Integer id, String name, String val) {
        if (id != -1) {
            mjlists.get(id).addJlistValue(val);
        } else {
            jlists jl = new jlists(name);
            jl.addJlistValue(val);
            mjlists.add(jl);
        }
    }

    public synchronized Boolean removeVal(Integer id, String val) {
        if (id != -1) {
            mjlists.get(id).removeJlistValue(val);
            return true;
        }
        return false;
    }

    public synchronized String getVal(String name) {
        ArrayList<String> al = null;
        try {
            List<jlists> collect = mjlists.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
            al = collect.get(0).getJlistValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex);
        }
        if (al != null) {
            return String.join("\n", al);
        }
        return "слово отсутвует в словаре";
    }

}
