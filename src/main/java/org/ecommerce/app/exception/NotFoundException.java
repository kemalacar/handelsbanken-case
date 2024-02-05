package org.ecommerce.app.exception;

/**
 * @author Kemal Acar
 */

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg) {
        super(msg);
    }
}

