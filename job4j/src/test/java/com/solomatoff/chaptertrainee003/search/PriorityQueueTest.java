package com.solomatoff.chaptertrainee003.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("prior5", 5));
        queue.put(new Task("prior1", 1));
        queue.put(new Task("prior3", 3));
        queue.put(new Task("prior8", 8));
        queue.put(new Task("prior0", 0));
        queue.put(new Task("prior7", 7));
        queue.put(new Task("double_prior3", 3));

        Task result = queue.take();
        assertThat(result.getDesc(), is("prior0"));
    }
}