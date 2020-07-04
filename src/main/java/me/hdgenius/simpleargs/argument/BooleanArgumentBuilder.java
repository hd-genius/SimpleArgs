package me.hdgenius.simpleargs.argument;

public class BooleanArgumentBuilder extends ArgumentBuilder<Boolean> {

    @Override
    public Argument<Boolean> createArgument() {
        return new BooleanArgument(isRequired, identifier, possibleValues, defaultValue);
    }

}
