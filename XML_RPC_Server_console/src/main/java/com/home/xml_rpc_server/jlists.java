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
public class jlists {
    private String name;
    
    private ArrayList<String> jlistValue = new ArrayList<String>();

    public jlists(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getJlistValue() {
        return jlistValue;
    }

    public void addJlistValue(String val) {
        this.jlistValue.add(val);
    }
    
}
