package me.hdgenius.simpleargs.argument;

import me.hdgenius.simpleargs.exception.UnsupportedArgumentTypeException;

import java.util.function.Function;

public class ArgumentBuilderImpl<T> implements ArgumentBuilder<T> {

    private final Class<T> typeOfArgument;
    private boolean isRequired = true;
    private T[] possibleValues;
    private T defaultValue;
    private String identifier;

    ArgumentBuilderImpl(final Class<T> typeOfArgument) {
        this.typeOfArgument = typeOfArgument;
    }

    public ArgumentBuilderImpl<T> isOptional() {
        isRequired = false;
        return this;
    }

    public ArgumentBuilderImpl<T> isRequired() {
        isRequired = true;
        return this;
    }

    public ArgumentBuilderImpl<T> withIdentifier(final String identifier) {
        this.identifier = identifier;
        return this;
    }

    public ArgumentBuilderImpl<T> withPossibleValues(final T... possibleValues) {
        this.possibleValues = possibleValues;
        return this;
    }

    public ArgumentBuilderImpl<T> withDefaultValue(final T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Argument<T> createArgument() {
        final Function<String, T> argumentParser = ArgumentParserRepository.getInstance().getParserForType(typeOfArgument)
                .orElseThrow(() -> new UnsupportedArgumentTypeException());
        return new ArgumentImpl<T>(isRequired, identifier, possibleValues, defaultValue, argumentParser);
    }

    static {
        final ArgumentParserRepository repository = ArgumentParserRepository.getInstance();
        repository.registerParserForType(Boolean::parseBoolean, Boolean.class);
        repository.registerParserForType(Function.identity(), String.class);
        repository.registerParserForType(Integer::parseInt, Integer.class);
        repository.registerParserForType(Long::parseLong, Long.class);
    }
}
