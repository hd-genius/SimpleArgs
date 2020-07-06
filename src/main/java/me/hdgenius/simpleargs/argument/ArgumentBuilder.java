package me.hdgenius.simpleargs.argument;

public interface ArgumentBuilder<T> {

    /**
     *
     * @param argumentType the class of the argument to build
     * @param <T> the type of the argument to build
     * @return an argument builder that will build an argument for the specified type of class
     */
    static <T> ArgumentBuilder<T> forType(final Class<T> argumentType) {
        return new ArgumentBuilderImpl<>(argumentType);
    }

    ArgumentBuilder<T> isOptional();

    ArgumentBuilder<T> isRequired();

    ArgumentBuilder<T> withIdentifier(final String identifier);

    ArgumentBuilder<T> withPossibleValues(final T... possibleValues);

    /**
     * only effects optional arguments
     * @param defaultValue
     * @return
     */
    ArgumentBuilder<T> withDefaultValue(final T defaultValue);

    /**
     *
     * @return an argument that is created from the current builder configuration
     */
    Argument<T> createArgument();

}
