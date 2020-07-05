package me.hdgenius.simpleargs.argument;

import me.hdgenius.simpleargs.exception.InvalidValueException;

import java.util.Arrays;
import java.util.function.Function;

public class ArgumentImpl<T> implements Argument<T> {

    private final boolean isRequired;
    private final String identifier;
    private final T[] possibleValues;
    private final T defaultValue;
    final Function<String, T> argumentParser;
    private T value;

    public ArgumentImpl(final boolean isRequired,
                        final String identifier,
                        final T[] possibleValues,
                        final T defaultValue,
                        final Function<String, T> argumentParser) {
        this.isRequired = isRequired;
        this.identifier = identifier;
        this.possibleValues = possibleValues;
        this.defaultValue = defaultValue;
        this.argumentParser = argumentParser;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void acceptValue(final String valueToAccept) {
        final T parsedValue = argumentParser.apply(valueToAccept);
        if (isOneOfThePossibleValues(parsedValue)) {
            value = parsedValue;
        } else {
            throw new InvalidValueException("The value " + valueToAccept + " is not one of the possible values for the parameter " + identifier);
        }
    }

    public T getValue() {
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    private boolean isOneOfThePossibleValues(final T value) {
        if (possibleValues != null) {
            return Arrays.stream(possibleValues).anyMatch(value::equals);
        } else {
            return true;
        }
    }

}
