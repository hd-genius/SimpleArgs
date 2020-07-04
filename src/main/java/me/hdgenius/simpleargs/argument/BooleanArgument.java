package me.hdgenius.simpleargs.argument;

public class BooleanArgument extends Argument<Boolean> {

    public BooleanArgument(final boolean isRequired, final String identifier, final Boolean[] possibleValues, final Boolean defaultValue) {
        super(isRequired, identifier, possibleValues, defaultValue);
    }

    @Override
    protected Boolean parseValue(final String valueToParse) {
        return Boolean.parseBoolean(valueToParse);
    }
}
