package com.redriver.project.exceptions;

/**
 * Super basic Exception - I just wanted to show quickly how I would implement some error handling so the errors being
 * returned would be formatted and make more sense - and could also be logged nicely.
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message){
        super(message);
    }
}
