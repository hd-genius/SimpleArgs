package me.hdgenius.simpleargs.argument;

public abstract class ArgumentBuilder<T> {

    protected boolean isRequired = true;
    protected T[] possibleValues;
    protected T defaultValue;
    protected String identifier;

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

    abstract public Argument<T> createArgument();
}
