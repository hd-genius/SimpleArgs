package me.hdgenius.simpleargs.argument;

import me.hdgenius.simpleargs.exception.UnsupportedArgumentTypeException;

import java.util.function.Function;

public class ArgumentBuilder<T> {

    private final Class<T> typeOfArgument;
    private boolean isRequired = true;
    private T[] possibleValues;
    private T defaultValue;
    private String identifier;

    /**
     *
     * @param argumentType the class of the argument to build
     * @param <T> the type of the argument to build
     * @return an argument builder that will build an argument for the specified type of class
     */
    public static <T> ArgumentBuilder<T> forType(final Class<T> argumentType) {
        return new ArgumentBuilder<>(argumentType);
    }

    ArgumentBuilder(final Class<T> typeOfArgument) {
        this.typeOfArgument = typeOfArgument;
    }

    public ArgumentBuilder<T> isOptional() {
        isRequired = false;
        return this;
    }

    public ArgumentBuilder<T> isRequired() {
        isRequired = true;
        return this;
    }

    public ArgumentBuilder<T> withIdentifier(final String identifier) {
        this.identifier = identifier;
        return this;
    }

    public ArgumentBuilder<T> withPossibleValues(final T... possibleValues) {
        this.possibleValues = possibleValues;
        return this;
    }

    /**
     * only effects optional arguments
     * @param defaultValue
     * @return
     */
    public ArgumentBuilder<T> withDefaultValue(final T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     *
     * @return an argument that is created from the current builder configuration
     */
    public Argument<T> createArgument() {
        final Function<String, T> argumentParser = ArgumentParserRepository.getInstance().getCreatorForType(typeOfArgument)
                .orElseThrow(() -> new UnsupportedArgumentTypeException());
        return new ArgumentImpl<T>(isRequired, identifier, possibleValues, defaultValue, argumentParser);
    }

    static {
        final ArgumentParserRepository repository = ArgumentParserRepository.getInstance();
        repository.registerCreatorForType(Boolean::parseBoolean, Boolean.class);
        repository.registerCreatorForType(Function.identity(), String.class);
        repository.registerCreatorForType(Integer::parseInt, Integer.class);
        repository.registerCreatorForType(Long::parseLong, Long.class);
    }
}
