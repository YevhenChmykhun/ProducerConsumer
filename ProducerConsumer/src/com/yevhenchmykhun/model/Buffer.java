package com.yevhenchmykhun.model;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer<T> {

	private static final int DEFAULT_SIZE = 10;

	private final int size;
	private Queue<T> queue;

	public Buffer() {
		this(DEFAULT_SIZE);
	}

	public Buffer(int size) {
		this.size = size;
		this.queue = new LinkedList<T>();
	}

	synchronized public T get() {
		while (queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		notify();
		return queue.poll();
	}

	synchronized public void put(T value) {
		while (queue.size() == size) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		queue.add(value);
		notify();
	}

}
