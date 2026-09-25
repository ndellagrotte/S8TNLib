/*
 * Copyright LWJGL. All rights reserved. License terms: https://www.lwjgl.org/license
 */
package com.gtnewhorizon.gtnhlib.bytebuf;

import java.nio.ByteBuffer;
import java.util.Objects;

import org.lwjgl.system.MemoryUtil;

/**
 * Simple index checks.
 *
 * <p>
 * They are the corresponding {@link java.util.Objects} methods. S8TNLib compiles for Java 21, so LWJGL's Java 8
 * fallbacks and the {@code classVersion()} marker of the multi-release variants are gone.
 * </p>
 */
public final class CheckIntrinsics {

    private CheckIntrinsics() {}

    public static int checkIndex(int index, int length) {
        return Objects.checkIndex(index, length);
    }

    public static int checkFromToIndex(int fromIndex, int toIndex, int length) {
        return Objects.checkFromToIndex(fromIndex, toIndex, length);
    }

    public static int checkFromIndexSize(int fromIndex, int size, int length) {
        return Objects.checkFromIndexSize(fromIndex, size, length);
    }

    public static ByteBuffer NewDirectByteBuffer(long address, int capacity) {
        return MemoryUtil.memByteBuffer(address, capacity);
    }

    public static MemoryUtilities.MemoryAllocator getLwjgl3ifyAllocator() {
        return MemoryManage.getInstance();
    }

}
