package com.example.soapcommon;

import javax.xml.namespace.QName;
import java.util.Set;

/**
 * Created by localadmin on 27/01/17.
 */
public interface ISoapMultiValuesProvider {

    Set<String> getMultiValues(QName name);

}
