package com.tci.BonusManager.exception;

public class TCIBonusManagerException extends RuntimeException {
    /**
     * Constructs a new TCIBonusManagerException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public TCIBonusManagerException(String message) {
        // Calls the constructor of the superclass (RuntimeException) with the specified message
        super(message);
    }
}
