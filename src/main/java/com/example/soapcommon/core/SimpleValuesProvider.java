package com.example.soapcommon.core;

import com.example.soapcommon.ISoapMultiValuesProvider;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class SimpleValuesProvider implements ISoapMultiValuesProvider {

    private Map<QName, Set<String>> multiValues;

    public SimpleValuesProvider() {
        multiValues = new HashMap<QName, Set<String>>();
    }

    public SimpleValuesProvider(Map<QName, Set<String>> multiValues) {
        multiValues = new HashMap<QName, Set<String>>();
        for (Map.Entry<QName, Set<String>> entry : multiValues.entrySet()) {
            multiValues.put(entry.getKey(), new HashSet<String>(entry.getValue()));
        }
    }

    @Override
    public Set<String> getMultiValues(QName name) {
        Set<String> result = multiValues.get(name);
        return result != null ? result : new HashSet<String>();
    }

    public void addMultiValues(QName name, Set<String> values) {
        multiValues.put(name, new HashSet<String>(values));
    }

}
