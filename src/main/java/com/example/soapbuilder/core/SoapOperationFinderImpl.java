package com.example.soapbuilder.core;

import com.example.soapbuilder.ISoapBuilder;
import com.example.soapbuilder.ISoapOperationBuilder;
import com.example.soapbuilder.ISoapOperationFinder;
import com.example.soapcommon.SoapBuilderException;
import com.example.soapcommon.SoapContext;
import org.assertj.core.util.Preconditions;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import java.util.ArrayList;
import java.util.List;

class SoapOperationFinderImpl implements ISoapOperationFinder {

    private final Binding binding;

    private String operationName;
    private String operationInputName;
    private String operationOutputName;
    private String soapAction;
    private ISoapBuilder builder;

    SoapOperationFinderImpl(ISoapBuilder builder, Binding binding) {
        this.binding = binding;
        this.builder = builder;
    }


    public ISoapOperationFinder name(String operationName) {
        Preconditions.checkNotNull(operationName);
        this.operationName = operationName;
        return this;
    }

    public ISoapOperationFinder soapAction(String soapAction) {
        Preconditions.checkNotNull(soapAction);
        this.soapAction = soapAction;
        return this;
    }

    public ISoapOperationFinder inputName(String inputName) {
        Preconditions.checkNotNull(inputName);
        this.operationInputName = inputName;
        return this;
    }

    public ISoapOperationFinder outputName(String outputName) {
        Preconditions.checkNotNull(outputName);
        this.operationOutputName = outputName;
        return this;
    }

    @SuppressWarnings("unchecked")
    public ISoapOperationBuilder find() {
        validateInput();
        List<ISoapOperationBuilder> found = new ArrayList<ISoapOperationBuilder>();
        for (BindingOperation operation : (List<BindingOperation>) binding.getBindingOperations()) {
            boolean condition = true;
            condition &= checkOperationName(operation);
            condition &= checkSoapAction(operation);
            condition &= checkOperationInputName(operation);
            condition &= checkOperationOutputName(operation);
            if(condition) {
                found.add(SoapOperationImpl.create(builder, binding, operation));
                if(found.size() > 1) {
                    throw new SoapBuilderException("Operation not unique - found more than one operation");
                }
            }
        }
        if(found.isEmpty()) {
            throw new SoapBuilderException("Found no operations");
        }
        return found.iterator().next();
    }

    public ISoapOperationBuilder find(SoapContext context) {
        ISoapOperationBuilder builder = find();
        builder.setContext(context);
        return builder;
    }

    private void validateInput() {
        boolean failed = true;
        failed &= this.operationName == null;
        failed &= this.soapAction == null;
        failed &= this.operationInputName == null;
        failed &= this.operationOutputName == null;
        if(failed) {
            throw new IllegalArgumentException("All finder properties cannot be null");
        }
    }

    private boolean checkOperationName(BindingOperation op) {
        if (this.operationName != null) {
            return this.operationName.equals(op.getOperation().getName());
        }
        return true;
    }

    private boolean checkSoapAction(BindingOperation op) {
        if (this.soapAction != null) {
            return this.soapAction.equals(SoapUtils.getSOAPActionUri(op));
        }
        return true;
    }

    private boolean checkOperationInputName(BindingOperation op) {
        if (this.operationInputName != null) {
            return this.operationInputName.equals(op.getOperation().getInput().getName());
        }
        return true;
    }

    private boolean checkOperationOutputName(BindingOperation op) {
        if (this.operationOutputName != null) {
            return this.operationOutputName.equals(op.getOperation().getOutput().getName());
        }
        return true;
    }


}
