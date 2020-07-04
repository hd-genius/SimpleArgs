package me.hdgenius.simpleargs;

import me.hdgenius.simpleargs.argument.Argument;
import me.hdgenius.simpleargs.exception.UnfulfilledRequiredArgumentException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArgumentParser {

    private final Argument<?>[] arguments;

    public static ArgumentParser forArguments(Argument<?>... arguments) {
        return new ArgumentParser(arguments);
    }

    private ArgumentParser(final Argument<?>[] arguments) {
        this.arguments = arguments;
    }

    public void parseArgumentStrings(final String[] argumentStrings) {
        final Map<String, String> valuesByIdentifier = new HashMap<>();
        // TODO: populate map with identifier value pairs
        final Set<String> argumentIdentifiers = valuesByIdentifier.keySet();
        validateAllRequiredArgumentsArePresent(argumentIdentifiers);
        valuesByIdentifier.forEach((identifier, value) -> {
            argumentForIdentifier(identifier).ifPresent(argument -> argument.acceptValue(value));
        });
    }

    private ArgumentAssignmentPair parseArgumentAssignmentPairFromString(final String string) {
        final String assignmentDelimiter = "=";
        final String[] splitString = string.split(assignmentDelimiter, 2);
        return new ArgumentAssignmentPair(splitString[0], splitString[1]);
    }

    private void validateAllRequiredArgumentsArePresent(final Collection<String> argumentIdentifiers) {
        final Set<String> requiredIdentifiersNotPresent = requiredArguments().stream()
                .map(Argument::getIdentifier)
                .filter(Predicate.not(argumentIdentifiers::contains))
                .collect(Collectors.toSet());
        final boolean requiredArgumentsMissing = !requiredIdentifiersNotPresent.isEmpty();
        if (requiredArgumentsMissing) {
            throw new UnfulfilledRequiredArgumentException(requiredIdentifiersNotPresent);
        }
    }

    private Set<Argument<?>> requiredArguments() {
        return Arrays.stream(arguments)
                .filter(Argument::isRequired)
                .collect(Collectors.toSet());
    }

    private Optional<Argument<?>> argumentForIdentifier(final String argumentIdentifier) {
        return Arrays.stream(arguments)
                .filter(argument -> argument.getIdentifier().equals(argumentIdentifier))
                .findAny();
    }

    private static class ArgumentAssignmentPair {
        public final String identifier;
        public final String value;

        public ArgumentAssignmentPair(final String identifier, final String value) {
            this.identifier = identifier;
            this.value = value;
        }
    }
}
