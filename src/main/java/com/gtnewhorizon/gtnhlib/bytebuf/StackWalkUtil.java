/*
 * Copyright LWJGL. All rights reserved. License terms: https://www.lwjgl.org/license
 */
package com.gtnewhorizon.gtnhlib.bytebuf;

import java.util.Arrays;
import java.util.Iterator;

import org.jetbrains.annotations.Nullable;

/**
 * Stack-walking utilities.
 *
 * <p>
 * They are implemented with {@code java.lang.StackWalker}, which has much lower overhead than a stack trace. S8TNLib
 * compiles for Java 21, so LWJGL's Java 8 variants and the {@code classVersion()} marker are gone.
 * </p>
 */
final class StackWalkUtil {
    private static final StackWalker STACK_WALKER =
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    private StackWalkUtil() {}

    static StackTraceElement[] stackWalkArray(Object[] a) {
        return Arrays.stream(((StackWalker.StackFrame[]) a)).map(StackWalker.StackFrame::toStackTraceElement)
                .toArray(StackTraceElement[]::new);
    }

    static Object stackWalkGetMethod(Class<?> after) {
        return STACK_WALKER.walk(stream -> {
            Iterator<StackWalker.StackFrame> iter = stream.iterator();
            iter.next();
            iter.next();

            StackWalker.StackFrame frame;
            do {
                frame = iter.next();
            } while (frame.getDeclaringClass() == after && iter.hasNext());

            return frame;
        });
    }

    private static boolean isSameMethod(StackWalker.StackFrame a, StackWalker.StackFrame b) {
        return isSameMethod(a, b, b.getMethodName());
    }

    private static boolean isSameMethod(StackWalker.StackFrame a, StackWalker.StackFrame b, String methodName) {
        return a.getDeclaringClass() == b.getDeclaringClass() && a.getMethodName().equals(methodName);
    }

    private static boolean isAutoCloseable(StackWalker.StackFrame element, StackWalker.StackFrame pushed) {
        if (isSameMethod(element, pushed, "$closeResource")) {
            return true;
        }

        return "kotlin.jdk7.AutoCloseableKt".equals(element.getClassName())
                && "closeFinally".equals(element.getMethodName());
    }

    static @Nullable Object stackWalkCheckPop(Class<?> after, Object pushedObj) {
        StackWalker.StackFrame pushed = (StackWalker.StackFrame) pushedObj;

        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk(stream -> {
            Iterator<StackWalker.StackFrame> iter = stream.iterator();
            iter.next();
            iter.next();

            StackWalker.StackFrame element;
            do {
                element = iter.next();
            } while (element.getDeclaringClass() == after && iter.hasNext());

            if (isSameMethod(element, pushed)) {
                return null;
            }

            if (iter.hasNext() && isAutoCloseable(element, pushed)) {
                element = iter.next();
                if (isSameMethod(element, pushed)) {
                    return null;
                }
            }

            return element;
        });
    }

    static Object[] stackWalkGetTrace() {
        return StackWalker.getInstance().walk(stream -> stream.skip(2)
                .dropWhile(frame -> frame.getClassName().startsWith("org.lwjgl.system.Memory"))
                .toArray(StackWalker.StackFrame[]::new));
    }

}
