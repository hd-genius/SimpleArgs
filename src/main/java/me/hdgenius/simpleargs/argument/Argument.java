package me.hdgenius.simpleargs.argument;

public interface Argument<T> {

    /**
     *
     * @return true if this argument is required and false otherwise
     */
    boolean isRequired();

    /**
     *
     * @return true if this argument is optional and false otherwise
     */
    default boolean isOptional() {
        return !isRequired();
    }

    /**
     *
     * @return the string identifier for this argument
     */
    String getIdentifier();

    /**
     *
     * @param valueToAccept a string representation of the value that was assigned to this argument
     */
    void acceptValue(final String valueToAccept);

    /**
     *
     * @return the value assigned to this argument or the default value if no value was provided
     */
    T getValue();
}
