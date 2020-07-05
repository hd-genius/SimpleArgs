package me.hdgenius.simpleargs.argument;

import java.util.HashMap;
import java.util.Map;

public class ArgumentCreatorRepository {

    final Map<Class<?>, ArgumentCreator<?>> argumentCreatorLookup = new HashMap<>();


    public <T> void registerCreatorForType(final ArgumentCreator<T> argumentCreator, final Class<T> type) {
        argumentCreatorLookup.put(type, argumentCreator);
    }

    public <T> ArgumentCreator<T> getCreatorForType(final Class<T> type) {
        return (ArgumentCreator<T>)argumentCreatorLookup.get(type);
    }

    @FunctionalInterface
    interface ArgumentCreator<T> {
        Argument<T> createArgument(boolean isRequired, String identifier, T[] possibleValues, T defaultValue);
    }
}
