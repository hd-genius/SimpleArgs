package me.hdgenius.simpleargs.exception;

import java.util.Collection;

public class UnfulfilledRequiredArgumentException extends ArgumentParseException {

    public UnfulfilledRequiredArgumentException(final Collection<String> missingArgumentIdentifiers) {
        super("An argument was expected for the following identifiers but none was found: " + missingArgumentIdentifiers.toString());
    }

}
