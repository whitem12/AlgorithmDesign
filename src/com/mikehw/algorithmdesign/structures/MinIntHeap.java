package com.mikehw.algorithmdesign.structures;

import java.util.Arrays;

public class MinIntHeap {

	private int size;
	private int[] heap;

	public MinIntHeap() {
		this(256);
	}

	public MinIntHeap(int capacity) {
		size = 0;
		heap = new int[capacity];
	}

	// Gets the size of the heap
	public int getSize() {
		return size;
	}

	private int getParentIndex(int index) {
		if (index == 0) {
			return -1;
		}
		return (index - 1) / 2;
	}

	private int getLeftChildIndex(int index) {
		return (index * 2) + 1;
	}

	private int getRightChildIndex(int index) {
		return (index * 2) + 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) + 1 <= size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) + 1 <= size;
	}

	private void swap(int indexA, int indexB) {
		int temp = heap[indexA];
		heap[indexA] = heap[indexB];
		heap[indexB] = temp;
	}

	private void bubbleUp(int index) {
		int parentIndex = getParentIndex(index);
		if (parentIndex == -1) {
			return;
		}
		if (heap[index] < heap[parentIndex]) {
			swap(index, parentIndex);
			bubbleUp(parentIndex);
		}
	}

	private void bubbleDown(int index) {
		int leftChildIndex = getLeftChildIndex(index);
		int rightChildIndex = getRightChildIndex(index);

		if(hasLeftChild(index) && heap[leftChildIndex] < heap[index]){
			swap(index, leftChildIndex);
			bubbleDown(leftChildIndex);
		}

		if(hasRightChild(index) && heap[rightChildIndex] < heap[index]){
			swap(index, rightChildIndex);
			bubbleDown(rightChildIndex);
		}
	}

	public void add(int value) {
		// Grow the array if needed
		if (heap.length == size) {
			int[] tempHeap = new int[heap.length * 2];
			System.arraycopy(heap, 0, tempHeap, 0, size);
			heap = tempHeap;
		}
		// Add to the end of the array
		size++;
		heap[size - 1] = value;
		bubbleUp(size - 1);
	}

	public int peek() {
		if (size < 1) {
			throw new IndexOutOfBoundsException("No elements in Heap");
		}
		return heap[0];
	}

	public int poll() {
		if (size < 1) {
			throw new IndexOutOfBoundsException("No elements in Heap");
		}
		int returnVal = heap[0];
		size--;
		if(size > 0){
			// Move the end of the heap to the top, move the top to the end which won't be referenced again
			swap(0, size);

			bubbleDown(0);
		}
		return returnVal;
	}

	@Override
	public String toString() {
		if(size == 0){
			return "MinIntHeap{ empty }";
		}
		return "MinIntHeap{" + "size=" + size + ", heap=" + Arrays.toString(Arrays.copyOfRange(heap, 0, size - 1)) + "}";
	}
}
