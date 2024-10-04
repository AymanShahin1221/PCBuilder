package com.app.exception;

public class InvalidApiResponseException extends Exception
{
    public InvalidApiResponseException(String message)
    {
        super(message);
    }
}
