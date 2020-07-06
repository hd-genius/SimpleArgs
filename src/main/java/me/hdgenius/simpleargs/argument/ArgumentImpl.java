package me.hdgenius.simpleargs.argument;

import java.util.function.Function;

public class ArgumentImpl<T> implements Argument<T> {

    private final boolean isRequired;
    private final String identifier;
    private final T defaultValue;
    final Function<String, T> argumentParser;
    private T value;

    public ArgumentImpl(final boolean isRequired,
                        final String identifier,
                        final T defaultValue,
                        final Function<String, T> argumentParser) {
        this.isRequired = isRequired;
        this.identifier = identifier;
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
        value = argumentParser.apply(valueToAccept);
    }

    public T getValue() {
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

}
