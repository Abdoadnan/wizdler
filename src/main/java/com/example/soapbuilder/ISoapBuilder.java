package com.example.soapbuilder;



import com.example.soapcommon.SoapContext;

import javax.wsdl.Binding;
import javax.xml.namespace.QName;
import java.util.List;

public interface ISoapBuilder {

    List<ISoapOperation> getOperations();

    ISoapOperationFinder operation();

    SoapContext getContext();

    ISoapOperationBuilder getOperationBuilder(ISoapOperation operation);

    String buildInputMessage(ISoapOperation operation);

    String buildInputMessage(ISoapOperation operation, SoapContext context);

    String buildOutputMessage(ISoapOperation operation);

    String buildOutputMessage(ISoapOperation operation, SoapContext context);

    String buildFault(String code, String message);

    String buildFault(String code, String message, SoapContext context);

    String buildEmptyFault();

    String buildEmptyFault(SoapContext context);

    String buildEmptyMessage();

    String buildEmptyMessage(SoapContext context);

    QName getBindingName();

    Binding getBinding();

    List<String> getServiceUrls();

    void validateInputMessage(ISoapOperation operation, String message);

    void validateInputMessage(ISoapOperation operation, String message, boolean strict);

    void validateOutputMessage(ISoapOperation operation, String message);

    void validateOutputMessage(ISoapOperation operation, String message, boolean strict);

    boolean isRpc();

    boolean isInputSoapEncoded(ISoapOperation operation);

    boolean isOutputSoapEncoded(ISoapOperation operation);
}
