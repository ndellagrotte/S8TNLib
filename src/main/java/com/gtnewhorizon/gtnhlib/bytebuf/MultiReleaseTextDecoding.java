package com.gtnewhorizon.gtnhlib.bytebuf;

import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;

/**
 * String decoding utilities.
 *
 * <p>
 * They delegate to LWJGL's {@link MemoryUtil}, which decodes into compact strings (JEP 254) directly. S8TNLib
 * compiles for Java 21, so the {@code classVersion()} marker of the multi-release variants is gone.
 * </p>
 */
final class MultiReleaseTextDecoding {

    private MultiReleaseTextDecoding() {}

    /** @see MemoryUtilities#memUTF8(ByteBuffer, int, int) */
    static String decodeUTF8(long source, int length) {
        return MemoryUtil.memUTF8(source, length);
    }
}
