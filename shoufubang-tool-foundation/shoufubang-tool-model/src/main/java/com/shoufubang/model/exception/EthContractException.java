package com.shoufubang.model.exception;
public class EthContractException extends Exception{

    public EthContractException(Throwable cause) {
        super(cause);
    }
    public EthContractException(String message, Throwable cause) {
        super(message, cause);
    }
}