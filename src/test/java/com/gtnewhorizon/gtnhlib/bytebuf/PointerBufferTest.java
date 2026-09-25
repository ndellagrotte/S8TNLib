package com.gtnewhorizon.gtnhlib.bytebuf;

import static com.gtnewhorizon.gtnhlib.bytebuf.MemoryUtilities.NULL;
import static com.gtnewhorizon.gtnhlib.bytebuf.Pointer.POINTER_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.lwjgl.PointerBuffer;

/**
 * S8TNLib's own tests of the {@link PointerBuffer} path, which nothing in Demonica calls. Up to 0.1.1,
 * {@code memPointerBuffer} counted its capacity in bytes, so the buffer held an eighth of the pointers asked for.
 */
class PointerBufferTest {

    @Test
    void memPointerBufferHoldsCapacityPointers() {
        long address = MemoryUtilities.nmemAllocChecked(4L * POINTER_SIZE);
        try {
            PointerBuffer buffer = MemoryUtilities.memPointerBuffer(address, 4);
            assertEquals(4, buffer.capacity());
            assertEquals(address, buffer.address0());
            buffer.put(3, 0x1234L);
            assertEquals(0x1234L, MemoryUtilities.memGetAddress(address + 3L * POINTER_SIZE));

            assertNull(MemoryUtilities.memPointerBufferSafe(NULL, 4));
            PointerBuffer safe = MemoryUtilities.memPointerBufferSafe(address, 4);
            assertNotNull(safe);
            assertEquals(4, safe.capacity());
            assertEquals(0x1234L, safe.get(3));
        } finally {
            MemoryUtilities.nmemFree(address);
        }
    }

    @Test
    void stackPointerBuffersHoldTheRequestedPointers() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            PointerBuffer malloc = stack.mallocPointer(3);
            assertEquals(3, malloc.capacity());
            malloc.put(2, 0x30L);
            assertEquals(0x30L, malloc.get(2));

            PointerBuffer calloc = stack.callocPointer(2);
            assertEquals(2, calloc.capacity());
            assertEquals(NULL, calloc.get(0));
            assertEquals(NULL, calloc.get(1));

            PointerBuffer three = stack.pointers(0x10L, 0x20L, 0x30L);
            assertEquals(3, three.capacity());
            assertEquals(0x10L, three.get(0));
            assertEquals(0x20L, three.get(1));
            assertEquals(0x30L, three.get(2));

            PointerBuffer five = stack.pointers(1L, 2L, 3L, 4L, 5L);
            assertEquals(5, five.capacity());
            assertEquals(5, five.remaining());
            assertEquals(5L, five.get(4));
        }
    }
}
