package me.hdgenius.simpleargs.argument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BooleanArgumentBuilderTest {

    @Test
    @DisplayName("test that the created argument is required when the isRequired method is called")
    public void testThatCreatedArgumentIsRequiredWhenTheIsRequiredMethodIsCalled() {
        final Argument<Boolean> argument = new BooleanArgumentBuilder().isRequired().createArgument();
        assertThat(argument.isRequired(), is(true));
    }

    @Test
    @DisplayName("test that the created argument is optional when the isOptional method is called")
    public void testThatCreatedArgumentIsOptionalWhenTheIsOptionalMethodIsCalled() {
        final Argument<Boolean> argument = new BooleanArgumentBuilder().isOptional().createArgument();
        assertThat(argument.isOptional(), is(true));
    }

}
