package com.martin.fowler.chapter1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
	private String name;
	private List<Rental> listOfRents = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		listOfRents.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		Iterator<Rental> rentals = this.listOfRents.iterator();
		StringBuilder result = new StringBuilder().append("Rental Record for " + getName() + "\n");
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			// determine amounts for each line
			// show figures for this rental
			result.append("\t" + each.getMovie().getTitle() + "\t").append(String.valueOf(each.getCharge()) + "\n");

		} // add footer lines
		result.append("Amount owed is ").append(String.valueOf(this.getTotalCharge()) + "\n");
		result.append("You earned ").append(String.valueOf(this.getTotalFrequentRenterPoints()) + " frequent renter points");
		return result.toString();
	}

	public String htmlStatement() {
		Iterator<Rental> rentals = this.listOfRents.iterator();
		StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n");
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			// show figures for each rental
			result.append(each.getMovie().getTitle() + ": ").append(String.valueOf(each.getCharge()) + "<BR>\n");
		} // add footer lines
		result.append("<P>You owe <EM>").append(String.valueOf(this.getTotalCharge()) + "</EM><P>\n");
		result.append("On this rental you earned <EM>").append(String.valueOf(this.getTotalFrequentRenterPoints())
				+ "</EM> frequent renter points<P>");
		return result.toString();
	}

	private double getTotalCharge() {
		double result = 0;
		Iterator<Rental> rentals = this.listOfRents.iterator();
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			result += each.getCharge();
		}
		return result;
	}

	private int getTotalFrequentRenterPoints() {
		int result = 0;
		Iterator<Rental> rentals = this.listOfRents.iterator();
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}

	/**
	 * @return the rentals
	 */
	public List<Rental> getRentals() {
		return listOfRents;
	}

	/**
	 * @param rentals the rentals to set
	 */
	public void setRentals(List<Rental> rentals) {
		this.listOfRents = rentals;
	}
}
