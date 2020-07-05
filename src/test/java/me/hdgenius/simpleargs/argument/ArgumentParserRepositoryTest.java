package me.hdgenius.simpleargs.argument;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ArgumentParserRepositoryTest {

    private ArgumentParserRepository argumentParserRepository;

    @BeforeEach
    public void setup() {
        argumentParserRepository = new ArgumentParserRepository();
    }

    @Test
    public void testThatArgumentParsersAreCorrectlyRegistered() {
        final Function<String, Boolean> expectedParser = input -> true;
        argumentParserRepository.registerParserForType(expectedParser, Boolean.class);
        final Function<String, Boolean> registeredParser = argumentParserRepository.getParserForType(Boolean.class)
                .orElseThrow();
        assertThat(registeredParser, equalTo(expectedParser));
    }

}
