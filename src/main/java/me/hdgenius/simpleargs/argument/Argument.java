package me.hdgenius.simpleargs.argument;

import me.hdgenius.simpleargs.exception.InvalidValueException;

import java.util.Arrays;
import java.util.function.Function;

public class Argument<T> {

    private final boolean isRequired;
    private final String identifier;
    private final T[] possibleValues;
    private final T defaultValue;
    final Function<String, T> argumentParser;
    private T value;

    public Argument(final boolean isRequired,
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

    /**
     *
     * @return true if this argument is required and false otherwise
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     *
     * @return true if this argument is optional and false otherwise
     */
    public boolean isOptional() {
        return !isRequired();
    }

    /**
     *
     * @return the string identifier for this argument
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     *
     * @param valueToAccept a string representation of the value that was assigned to this argument
     */
    public void acceptValue(final String valueToAccept) {
        final T parsedValue = argumentParser.apply(valueToAccept);
        if (isOneOfThePossibleValues(parsedValue)) {
            value = parsedValue;
        } else {
            throw new InvalidValueException("The value " + valueToAccept + " is not one of the possible values for the parameter " + identifier);
        }
    }

    /**
     *
     * @return the value assigned to this argument or the default value if no value was provided
     */
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
