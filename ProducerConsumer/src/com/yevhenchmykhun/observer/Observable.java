package com.yevhenchmykhun.observer;

public interface Observable {

	void addObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObserver(int value);

}
