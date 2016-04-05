package com.yevhenchmykhun.model;

import java.util.Random;

public class IntegerGenerator {

	private Range range;
	private Random random;

	public IntegerGenerator(Range range) {
		this.range = range;
		random = new Random(System.currentTimeMillis());
	}

	public int getNextInteger() {
		return random.nextInt(range.getUpperBound()) + range.getLowerBound();
	}

}
