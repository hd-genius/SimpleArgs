package me.hdgenius.simpleargs.argument;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ArgumentParserRepository {

    static ArgumentParserRepository instance;

    final Map<Class<?>, Function<String, ?>> argumentCreatorLookup = new HashMap<>();

    public static ArgumentParserRepository getInstance() {
        if (instance == null) {
            instance = new ArgumentParserRepository();
        }
        return instance;
    }

    public <T> void registerCreatorForType(final Function<String, T> argumentParser, final Class<T> type) {
        argumentCreatorLookup.put(type, argumentParser);
    }

    public <T> Optional<Function<String, T>> getCreatorForType(final Class<T> type) {
        // TODO: investigate looking up subclasses
        return Optional.of((Function<String, T>)argumentCreatorLookup.get(type));
    }
}
