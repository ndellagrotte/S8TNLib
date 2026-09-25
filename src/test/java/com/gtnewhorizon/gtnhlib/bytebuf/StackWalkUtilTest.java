package com.gtnewhorizon.gtnhlib.bytebuf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * S8TNLib's own test of the stack-walking helpers behind {@code DebugMemoryStack}, which nothing else calls. The
 * walks skip their own frame, their caller's, and every frame of the class they are told to skip.
 */
class StackWalkUtilTest {

    /** Stands in for {@code MemoryStack}: the walks skip its frames, as they skip {@code MemoryStack}'s. */
    private static final class Frames {

        static Object push() {
            return pushThrough();
        }

        private static Object pushThrough() {
            return StackWalkUtil.stackWalkGetMethod(Frames.class);
        }

        static Object pop(Object pushed) {
            return StackWalkUtil.stackWalkCheckPop(Frames.class, pushed);
        }
    }

    @Test
    void stackWalkGetMethodNamesTheCallerOutsideTheSkippedClass() {
        StackWalker.StackFrame frame = (StackWalker.StackFrame) Frames.push();
        assertEquals(StackWalkUtilTest.class, frame.getDeclaringClass());
        assertEquals("stackWalkGetMethodNamesTheCallerOutsideTheSkippedClass", frame.getMethodName());
    }

    @Test
    void stackWalkCheckPopAcceptsAPopFromThePushingMethod() {
        Object pushed = Frames.push();
        assertNull(Frames.pop(pushed));
    }

    @Test
    void stackWalkCheckPopReportsAPopFromAnotherMethod() {
        Object pushed = Frames.push();
        StackWalker.StackFrame popped = (StackWalker.StackFrame) popElsewhere(pushed);
        assertNotNull(popped);
        assertEquals(StackWalkUtilTest.class, popped.getDeclaringClass());
        assertEquals("popElsewhere", popped.getMethodName());
    }

    private static Object popElsewhere(Object pushed) {
        return Frames.pop(pushed);
    }
}
