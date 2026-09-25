package com.gtnewhorizon.gtnhlib.client.renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.lwjgl.system.MemoryUtil;

/**
 * S8TNLib's own test: a thread's main capture buffers are freed once the thread has ended. Up to 0.1.1 they were
 * allocated on the thread's first capture and never freed. It counts the thread's live native allocations through
 * LWJGL's debug allocator, which the test task turns on.
 */
class TessellatorManagerBuffersTest {

    @Test
    void aThreadsMainBufferIsFreedAfterTheThreadEnds() throws Exception {
        assertTrue(Boolean.getBoolean("org.lwjgl.util.DebugAllocator"),
                "the test task sets org.lwjgl.util.DebugAllocator, which the count needs");
        CountDownLatch captured = new CountDownLatch(1);
        CountDownLatch released = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            TessellatorManager.startCapturingDirect(tessellator -> false);
            TessellatorManager.stopCapturingDirect();
            captured.countDown();
            try {
                released.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "capturing");
        thread.start();
        captured.await();
        long threadId = thread.threadId();
        assertEquals(1, liveAllocations(threadId), "the main callback buffer, while the thread lives");
        released.countDown();
        thread.join();

        long deadline = System.nanoTime() + 10_000_000_000L;
        int live;
        do {
            System.gc();
            Thread.sleep(50);
            live = liveAllocations(threadId);
        } while (live != 0 && System.nanoTime() < deadline);
        assertEquals(0, live, "no live buffer once the thread has ended and a GC has run");
    }

    private static int liveAllocations(long threadId) {
        AtomicInteger count = new AtomicInteger();
        MemoryUtil.memReport((address, memory, id, threadName, stacktrace) -> {
            if (id == threadId) {
                count.incrementAndGet();
            }
        });
        return count.get();
    }
}
