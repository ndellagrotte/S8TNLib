package com.gtnewhorizon.gtnhlib.bytebuf;

import static com.gtnewhorizon.gtnhlib.bytebuf.MemoryUtilities.NULL;
import static com.gtnewhorizon.gtnhlib.bytebuf.Pointer.POINTER_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;

/**
 * S8TNLib's own tests of the {@link PointerBuffer} path, which nothing in Demonica calls. Up to 0.1.1,
 * {@code memPointerBuffer} counted its capacity in bytes, so the buffer held an eighth of the pointers asked for,
 * {@code memAddress(PointerBuffer)} called itself until the stack overflowed, and {@code APIUtil}'s {@code apiArray}
 * variants returned the end of the array they had filled.
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

    @Test
    void memAddressOfAPointerBufferIsTheAddressAtItsPosition() {
        PointerBuffer buffer = MemoryUtilities.memAllocPointer(4);
        try {
            buffer.position(2);
            assertEquals(MemoryUtil.memAddress(buffer), MemoryUtilities.memAddress(buffer));
            assertEquals(buffer.address0() + 2L * POINTER_SIZE, MemoryUtilities.memAddress(buffer));
            assertEquals(MemoryUtil.memAddress(buffer, 3), MemoryUtilities.memAddress(buffer, 3));
            assertEquals(buffer.address0() + 3L * POINTER_SIZE, MemoryUtilities.memAddress(buffer, 3));
        } finally {
            MemoryUtilities.nmemFree(buffer.address0());
        }
    }

    @Test
    void memReallocOfAPointerBufferKeepsItsValuesAndPosition() {
        PointerBuffer buffer = MemoryUtilities.memAllocPointer(4);
        for (int i = 0; i < 4; i++) {
            buffer.put(i, 0x100L + i);
        }
        buffer.position(2);
        // The block is grown's from here on, and only grown is freed.
        PointerBuffer grown = MemoryUtilities.memRealloc(buffer, 8);
        try {
            assertEquals(8, grown.capacity());
            assertEquals(2, grown.position());
            for (int i = 0; i < 4; i++) {
                assertEquals(0x100L + i, grown.get(i));
            }
        } finally {
            MemoryUtilities.nmemFree(grown.address0());
        }
    }

    // --- APIUtil's pointer arrays. A result is checked against the frame before anything is read through it. ---

    @Test
    void apiArrayReturnsTheStartOfThePointerArray() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            long array = APIUtil.apiArray(stack, 0x10L, 0x20L, 0x30L);
            // The array is the frame's last allocation, so it starts at the stack pointer.
            assertEquals(stack.getPointerAddress(), array);
            assertEquals(0x10L, MemoryUtilities.memGetAddress(array));
            assertEquals(0x20L, MemoryUtilities.memGetAddress(array + POINTER_SIZE));
            assertEquals(0x30L, MemoryUtilities.memGetAddress(array + 2L * POINTER_SIZE));
        }
    }

    @Test
    void apiArrayOfBuffersStoresTheirAddresses() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer first = stack.malloc(3);
            ByteBuffer second = stack.malloc(5);
            long array = APIUtil.apiArray(stack, first, second);
            assertEquals(stack.getPointerAddress(), array);
            assertEquals(MemoryUtilities.memAddress(first), MemoryUtilities.memGetAddress(array));
            assertEquals(MemoryUtilities.memAddress(second), MemoryUtilities.memGetAddress(array + POINTER_SIZE));
        }
    }

    @Test
    void apiArraypOfBuffersStoresTheirLengthsBelowThePointers() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer first = stack.malloc(3);
            ByteBuffer second = stack.malloc(5);
            long frameTop = stack.getPointerAddress();
            long array = APIUtil.apiArrayp(stack, first, second);
            long lengths = array - 2L * POINTER_SIZE;
            assertWithinTheFrame(stack, lengths, array + 2L * POINTER_SIZE, frameTop);
            assertEquals(MemoryUtilities.memAddress(first), MemoryUtilities.memGetAddress(array));
            assertEquals(MemoryUtilities.memAddress(second), MemoryUtilities.memGetAddress(array + POINTER_SIZE));
            assertEquals(3L, MemoryUtilities.memGetAddress(lengths));
            assertEquals(5L, MemoryUtilities.memGetAddress(lengths + POINTER_SIZE));
        }
    }

    @Test
    void apiArrayOfStringsStoresTheEncodedAddresses() {
        RecordingEncoder encoder = new RecordingEncoder();
        try (MemoryStack stack = MemoryStack.stackPush()) {
            long array = APIUtil.apiArray(stack, encoder, "a", "bc");
            assertEquals(stack.getPointerAddress(), array);
            assertEquals(2, encoder.encoded.size());
            assertEquals(MemoryUtilities.memAddress(encoder.encoded.get(0)), MemoryUtilities.memGetAddress(array));
            assertEquals(MemoryUtilities.memAddress(encoder.encoded.get(1)),
                    MemoryUtilities.memGetAddress(array + POINTER_SIZE));
            assertEquals("bc", MemoryUtilities.memUTF8(MemoryUtilities.memGetAddress(array + POINTER_SIZE)));
        } finally {
            encoder.free();
        }
    }

    @Test
    void apiArrayiStoresIntLengthsBelowThePointers() {
        RecordingEncoder encoder = new RecordingEncoder();
        try (MemoryStack stack = MemoryStack.stackPush()) {
            long frameTop = stack.getPointerAddress();
            long array = APIUtil.apiArrayi(stack, encoder, "a", "bc");
            long lengths = array - 2L * Integer.BYTES;
            assertWithinTheFrame(stack, lengths, array + 2L * POINTER_SIZE, frameTop);
            assertEquals(MemoryUtilities.memAddress(encoder.encoded.get(0)), MemoryUtilities.memGetAddress(array));
            assertEquals(MemoryUtilities.memAddress(encoder.encoded.get(1)),
                    MemoryUtilities.memGetAddress(array + POINTER_SIZE));
            assertEquals(1, MemoryUtilities.memGetInt(lengths));
            assertEquals(2, MemoryUtilities.memGetInt(lengths + Integer.BYTES));
        } finally {
            encoder.free();
        }
    }

    @Test
    void apiArraypStoresPointerSizedLengthsBelowThePointers() {
        RecordingEncoder encoder = new RecordingEncoder();
        try (MemoryStack stack = MemoryStack.stackPush()) {
            long frameTop = stack.getPointerAddress();
            long array = APIUtil.apiArrayp(stack, encoder, "a", "bc");
            long lengths = array - 2L * POINTER_SIZE;
            assertWithinTheFrame(stack, lengths, array + 2L * POINTER_SIZE, frameTop);
            assertEquals(MemoryUtilities.memAddress(encoder.encoded.get(0)), MemoryUtilities.memGetAddress(array));
            assertEquals(MemoryUtilities.memAddress(encoder.encoded.get(1)),
                    MemoryUtilities.memGetAddress(array + POINTER_SIZE));
            assertEquals(1L, MemoryUtilities.memGetAddress(lengths));
            assertEquals(2L, MemoryUtilities.memGetAddress(lengths + POINTER_SIZE));
        } finally {
            encoder.free();
        }
    }

    @Test
    void apiArrayFreeFreesThePointedToBlocks() {
        // On an array the test builds itself. The blocks are freed only here: a wrong address would abort the JVM.
        long first = MemoryUtilities.nmemAllocChecked(8);
        long second = MemoryUtilities.nmemAllocChecked(8);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            PointerBuffer array = stack.pointers(first, second);
            APIUtil.apiArrayFree(array.address0(), 2);
        }
    }

    /**
     * Everything the call allocated lies between the stack pointer and the frame top from before the call:
     * {@code from} is the lowest address to be read, {@code to} one past the highest.
     */
    private static void assertWithinTheFrame(MemoryStack stack, long from, long to, long frameTop) {
        long pointer = stack.getPointerAddress();
        assertTrue(pointer <= from && from < to && to <= frameTop,
                () -> String.format("0x%X..0x%X is not within the frame 0x%X..0x%X", from, to, pointer, frameTop));
    }

    /** Encodes on the heap and records every buffer, so that the test frees them without trusting the array. */
    private static final class RecordingEncoder implements APIUtil.Encoder {

        final List<ByteBuffer> encoded = new ArrayList<>();

        @Override
        public ByteBuffer encode(CharSequence text, boolean nullTerminated) {
            ByteBuffer buffer = MemoryUtilities.memUTF8(text, nullTerminated);
            encoded.add(buffer);
            return buffer;
        }

        void free() {
            for (ByteBuffer buffer : encoded) {
                MemoryUtilities.memFree(buffer);
            }
        }
    }
}
