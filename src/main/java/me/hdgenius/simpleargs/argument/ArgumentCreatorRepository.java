package me.hdgenius.simpleargs.argument;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArgumentCreatorRepository {

    static ArgumentCreatorRepository instance;

    final Map<Class<?>, ArgumentCreator<?>> argumentCreatorLookup = new HashMap<>();

    public static ArgumentCreatorRepository getInstance() {
        if (instance == null) {
            instance = new ArgumentCreatorRepository();
        }
        return instance;
    }

    public <T> void registerCreatorForType(final ArgumentCreator<T> argumentCreator, final Class<T> type) {
        argumentCreatorLookup.put(type, argumentCreator);
    }

    public <T> Optional<ArgumentCreator<T>> getCreatorForType(final Class<T> type) {
        return Optional.of((ArgumentCreator<T>)argumentCreatorLookup.get(type));
    }
}
