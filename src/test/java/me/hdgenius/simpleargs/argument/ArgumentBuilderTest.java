package me.hdgenius.simpleargs.argument;

import me.hdgenius.simpleargs.exception.InvalidValueException;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class ArgumentBuilderTest {

    @Test
    @DisplayName("test that the created argument is required when the isRequired method is called")
    public void testThatCreatedArgumentIsRequiredWhenTheIsRequiredMethodIsCalled() {
        final Argument<Boolean> argument = ArgumentBuilder.forType(Boolean.class).isRequired().createArgument();
        assertThat(argument.isRequired(), is(true));
    }

    @Test
    @DisplayName("test that the created argument is optional when the isOptional method is called")
    public void testThatCreatedArgumentIsOptionalWhenTheIsOptionalMethodIsCalled() {
        final Argument<Boolean> argument = ArgumentBuilder.forType(Boolean.class).isOptional().createArgument();
        assertThat(argument.isOptional(), is(true));
    }

    @Nested
    public class BooleanArgumentTest {

        private Argument<Boolean> argument;

        @BeforeEach
        public void setup() {
            argument = ArgumentBuilder.forType(Boolean.class).createArgument();
        }

        @Test
        public void testThatCreatedBooleanArgumentsCorrectlyParseValues() {
            final String argumentInput = "true";
            argument.acceptValue(argumentInput);
            assertThat(argument.getValue(), equalTo(true));
        }

    }

}
