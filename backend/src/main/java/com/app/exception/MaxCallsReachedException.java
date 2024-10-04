package com.app.exception;

public class MaxCallsReachedException extends Exception
{
    public MaxCallsReachedException(String message)
    {
        super(message);
    }
}
