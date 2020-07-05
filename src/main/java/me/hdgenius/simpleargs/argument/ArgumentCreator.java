package me.hdgenius.simpleargs.argument;

@FunctionalInterface
interface ArgumentCreator<T> {
    Argument<T> createArgument(boolean isRequired, String identifier, T[] possibleValues, T defaultValue);
}
