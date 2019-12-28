package com.mikehw.algorithmdesign.test;

import com.mikehw.algorithmdesign.structures.MinIntHeap;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MinIntHeapTest {

	private MinIntHeap emptyHeap;
	private MinIntHeap smallHeap;
	private MinIntHeap largeHeap;

	@BeforeEach
	void setup(){
		emptyHeap = new MinIntHeap();
		smallHeap = new MinIntHeap(1);
		smallHeap.add(1);
		largeHeap = new MinIntHeap(1);
		largeHeap.add(5);
		largeHeap.add(3);
		largeHeap.add(8);
		largeHeap.add(1);
		largeHeap.add(0);
		largeHeap.add(10);
	}

	@org.junit.jupiter.api.Test
	void getSize() {
		assertEquals(0, emptyHeap.getSize());
		assertEquals(1, smallHeap.getSize());
		assertEquals(6, largeHeap.getSize());
		smallHeap.poll();
		assertEquals(0, smallHeap.getSize());
	}

	@org.junit.jupiter.api.Test
	void add() {
		emptyHeap.add(1);
		assertEquals(1, emptyHeap.getSize());
		assertEquals(1, emptyHeap.peek());
		largeHeap.add(-1);
		assertEquals(-1,largeHeap.peek());
	}

	@org.junit.jupiter.api.Test
	void peek() {
		assertEquals(smallHeap.peek(), 1);
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> emptyHeap.peek());
		assertEquals("No elements in Heap", e.getMessage());
	}

	@org.junit.jupiter.api.Test
	void poll() {
		assertThrows(IndexOutOfBoundsException.class, () -> emptyHeap.poll());
		assertEquals(0, largeHeap.poll());
		assertEquals(1, largeHeap.poll());
		assertEquals(3, largeHeap.poll());
		assertEquals(5, largeHeap.poll());
		assertEquals(8, largeHeap.poll());
		assertEquals(10, largeHeap.poll());
		assertThrows(IndexOutOfBoundsException.class, () -> largeHeap.poll());
	}
}