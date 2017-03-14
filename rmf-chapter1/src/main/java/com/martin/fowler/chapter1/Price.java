package com.martin.fowler.chapter1;

public interface Price {

	public abstract int getPriceCode();

	public abstract double getCharge(int daysRented);

	default int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}