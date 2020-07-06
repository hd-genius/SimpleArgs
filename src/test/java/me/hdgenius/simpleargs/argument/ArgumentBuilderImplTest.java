package me.hdgenius.simpleargs.argument;

import me.hdgenius.simpleargs.exception.InvalidValueException;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class ArgumentBuilderImplTest {

    @Test
    @DisplayName("test that the created argument is required when the isRequired method is called")
    public void testThatCreatedArgumentIsRequiredWhenTheIsRequiredMethodIsCalled() {
        final Argument<Boolean> argument = new ArgumentBuilderImpl<>(Boolean.class).isRequired().createArgument();
        assertThat(argument.isRequired(), is(true));
    }

    @Test
    @DisplayName("test that the created argument is optional when the isOptional method is called")
    public void testThatCreatedArgumentIsOptionalWhenTheIsOptionalMethodIsCalled() {
        final Argument<Boolean> argument = new ArgumentBuilderImpl<>(Boolean.class).isOptional().createArgument();
        assertThat(argument.isOptional(), is(true));
    }

    @Nested
    public class CreatedBooleanArgumentTest {

        private Argument<Boolean> argument;

        @BeforeEach
        public void setup() {
            argument = new ArgumentBuilderImpl<>(Boolean.class).createArgument();
        }

        @Test
        public void testThatCreatedBooleanArgumentsCorrectlyParseValues() {
            final String argumentInput = "true";
            argument.acceptValue(argumentInput);
            assertThat(argument.getValue(), equalTo(true));
        }

        @Test
        public void testThatAnExceptionIsThrownIfTheInputValueIsNotParsable() {
            Assertions.assertThrows(InvalidValueException.class, () -> argument.acceptValue("not a boolean"));
        }

    }

    @Nested
    public class CreatedIntegerArgumentTest {

        private Argument<Integer> argument;

        @BeforeEach
        public void setup() {
            argument = new ArgumentBuilderImpl<>(Integer.class).createArgument();
        }

        @Test
        public void testThatCreatedBooleanArgumentsCorrectlyParseValues() {
            final String argumentInput = "5";
            argument.acceptValue(argumentInput);
            assertThat(argument.getValue(), equalTo(5));
        }

        @Test
        public void testThatAnExceptionIsThrownIfTheInputValueIsNotParsable() {
            Assertions.assertThrows(InvalidValueException.class, () -> argument.acceptValue("not an integer boolean"));
        }

    }

}
