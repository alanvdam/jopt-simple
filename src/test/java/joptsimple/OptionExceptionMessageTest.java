/*
 The MIT License

 Copyright (c) 2004-2011 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package joptsimple;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
@RunWith( Parameterized.class )
public class OptionExceptionMessageTest {
    private final OptionException subject;
    private final String expectedMessage;

    public OptionExceptionMessageTest( OptionException subject, String expectedMessage ) {
        this.subject = subject;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Collection<?> exceptionsAndMessages() {
        return asList(
            new Object[] { new IllegalOptionSpecificationException( "," ), "',' is not a legal option character" },
            new Object[] { new IllegalOptionClusterException( "a" ), "Option cluster containing 'a' is illegal" },
            new Object[] { new MultipleArgumentsForOptionException( asList( "b", "c" ) ),
                "Found multiple arguments for option ['b', 'c'], but you asked for only one" },
            new Object[] { new OptionArgumentConversionException(
                asList( "c", "number" ), "d", Integer.class, null ),
                "Cannot convert argument 'd' of option ['c', 'number'] to class java.lang.Integer" },
            new Object[] { new OptionMissingRequiredArgumentException( asList( "e", "honest" ) ),
                "Option ['e', 'honest'] requires an argument" },
            new Object[] { new UnrecognizedOptionException( "f" ), "'f' is not a recognized option" },
            new Object[] { new MissingRequiredOptionException( asList( "g", "h" ) ),
                "Missing required option ['g', 'h']" }
        );
    }

    @Test
    public void givesCorrectExceptionMessage() {
        assertEquals( expectedMessage, subject.getLocalizedMessage() );
        assertEquals( expectedMessage, subject.getMessage() );
        assertEquals( subject.getClass().getName() + ": " + expectedMessage, subject.toString() );
    }
}
