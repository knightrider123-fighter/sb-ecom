package com.ecommerce.sbecom.exception;

public class CustomerDeatilsInvaildException extends RuntimeException{
    public CustomerDeatilsInvaildException(String erroeMessage,Long id){
        super(erroeMessage+" "+id);

    }
    
}
