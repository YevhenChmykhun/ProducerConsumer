package com.yevhenchmykhun.model;

import java.util.ArrayList;

import com.yevhenchmykhun.observer.Observer;

public class ProducerConsumer extends AbstractWorker {

	private Buffer<Integer> queueFrom;
	private Buffer<Integer> queueTo;

	public ProducerConsumer(long delay, Buffer<Integer> queueFrom,
			Buffer<Integer> queueTo) {
		super(delay);
		this.queueFrom = queueFrom;
		this.queueTo = queueTo;
		observers = new ArrayList<>();
		thread = new Thread(this, "Producer-Consumer");
		thread.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			int value = (int) Math.pow(queueFrom.get(), 2);
			notifyObserver(value);
			queueTo.put(value);
		}
	}

	@Override
	public void notifyObserver(int value) {
		for (Observer obj : observers) {
			obj.updateProducerConsumerTextArea(value);
		}
	}
}
