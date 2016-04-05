package com.yevhenchmykhun.model;

import java.util.List;

import com.yevhenchmykhun.observer.Observable;
import com.yevhenchmykhun.observer.Observer;

public abstract class AbstractWorker implements Runnable, Observable {

	protected long delay;
	protected Thread thread;
	protected List<Observer> observers;

	public AbstractWorker(long delay) {
		this.delay = delay;
	}

	@SuppressWarnings("deprecation")
	public void suspend() {
		thread.suspend();
	}

	@SuppressWarnings("deprecation")
	public void resume() {
		thread.resume();
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

}