package com.example.soapbuilder;

import com.example.soapcommon.SoapContext;

/**
 * Created by localadmin on 27/01/17.
 */
public interface ISoapOperationBuilder extends ISoapOperation {

    void setContext(SoapContext context);

    SoapContext getContext();

    String buildInputMessage();

    String buildInputMessage(SoapContext context);

    String buildOutputMessage();

    String buildOutputMessage(SoapContext context);

    String buildFault(String code, String message);

    String buildFault(String code, String message, SoapContext context);

    String buildEmptyFault();

    String buildEmptyFault(SoapContext context);

    String buildEmptyMessage();

    String buildEmptyMessage(SoapContext context);

    void validateInputMessage(String message);

    void validateInputMessage(String message, boolean strict);

    void validateOutputMessage(String message);

    void validateOutputMessage(String message, boolean strict);

}
