package com.example.soapbuilder;

import javax.xml.namespace.QName;

/**
 * Created by localadmin on 27/01/17.
 */
public interface ISoapOperation {

    QName getBindingName();

    String getOperationName();

    String getOperationInputName();

    String getOperationOutputName();

    String getSoapAction();

    boolean isRpc();

    boolean isInputSoapEncoded();

    boolean isOutputSoapEncoded();

}
