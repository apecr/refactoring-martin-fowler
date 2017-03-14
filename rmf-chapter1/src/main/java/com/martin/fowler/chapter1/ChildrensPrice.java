package com.martin.fowler.chapter1;

public class ChildrensPrice implements Price {

	@Override
	public int getPriceCode() {
		return Movie.CHILDRENS;
	}

	@Override
	public double getCharge(int daysRented) {
		double result = 1.5;
		if (daysRented > 2)
			result += (daysRented - 3) * 1.5;
		return result;
	}

}
