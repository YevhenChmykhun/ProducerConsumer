package com.yevhenchmykhun.model;

import java.util.ArrayList;

import com.yevhenchmykhun.observer.Observer;

public class Producer extends AbstractWorker {

	private IntegerGenerator generator;
	private Buffer<Integer> queue;

	public Producer(long delay, IntegerGenerator generator,
			Buffer<Integer> queue) {
		super(delay);
		this.queue = queue;
		this.generator = generator;
		observers = new ArrayList<>();
		thread = new Thread(this, "Producer");
		thread.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			int value = generator.getNextInteger();
			notifyObserver(value);
			queue.put(value);
		}
	}

	@Override
	public void notifyObserver(int value) {
		for (Observer obj : observers) {
			obj.updateProducerTextArea(value);
		}
	}
}
