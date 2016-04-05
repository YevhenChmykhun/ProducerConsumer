package com.yevhenchmykhun.model;

import java.util.ArrayList;

import com.yevhenchmykhun.observer.Observer;

public class Consumer extends AbstractWorker {

	private Buffer<Integer> queue;

	public Consumer(long delay, Buffer<Integer> queue) {
		super(delay);
		this.queue = queue;
		observers = new ArrayList<>();
		thread = new Thread(this, "Consumer");
		thread.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			notifyObserver(queue.get());
		}
	}

	@Override
	public void notifyObserver(int value) {
		for (Observer obj : observers) {
			obj.updateConsumerTextArea(value);
		}
	}

}
