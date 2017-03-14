package com.martin.fowler.chapter1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private static final String BARRA_T = "\t";
	private static final String RETORNO_CARRO = "\n";
	private Customer customer;
	private String name = "Customer Name";

	@Before
	public void setUp() {
		this.customer = new Customer(name);
	}

	@Test
	public void notNull() {
		assertNotNull(this.customer);
	}

	@Test
	public void statementEmptyRental() {
		// Given
		// When
		String statement = this.customer.statement();
		// Then
		assertEquals(stringToCompare(0.0, 0), statement);
	}

	@Test(expected = IllegalArgumentException.class)
	public void statementOneRentalWithNotAcceptedMovieMovie0DaysRented() {
		// Given
		this.customer.addRental(generateRental(generateMovieType(3), 0));
		// When
		this.customer.statement();
		// Then
		fail();
	}

	@Test
	public void statementOneRentalWithChildrenMovie0DaysRented() {
		// Given
		this.customer.addRental(generateRental(generateMovieType(Movie.CHILDRENS), 0));
		// When
		String statement = this.customer.statement();
		// Then
		assertEquals(stringToCompare(1.5, 1), statement);
	}

	@Test
	public void statementOneRentalWithChildrenMovie4DaysRented() {
		this.customer.addRental(generateRental(generateMovieType(Movie.CHILDRENS), 4));
		// When
		String statement = this.customer.statement();
		// Then
		assertEquals(stringToCompare(3.0, 1), statement);
	}

	@Test
	public void statementTwoRentalWithChildrenMovie4DaysRentedAndOtherKindsOfMovies() {
		this.customer.addRental(generateRental(generateMovieType(Movie.CHILDRENS), 4));
		this.customer.addRental(generateRental(generateMovieType(Movie.NEW_RELEASE), 4));
		this.customer.addRental(generateRental(generateMovieType(Movie.NEW_RELEASE), 1));
		this.customer.addRental(generateRental(generateMovieType(Movie.REGULAR), 4));
		this.customer.addRental(generateRental(generateMovieType(Movie.REGULAR), 2));
		// When
		String statement = this.customer.statement();
		// Then
		assertEquals(stringToCompare(25.0, 6), statement);
	}

	@Test
	public void htmlStatementOneRentalWithChildrenMovie0DaysRented() {
		// Given
		this.customer.addRental(generateRental(generateMovieType(Movie.CHILDRENS), 0));
		// When
		String statement = this.customer.htmlStatement();
		// Then
		assertEquals(htmlStringToCompare(1.5, 1), statement);
	}

	private String stringToCompare(double amountOwed, int amountEarned) {
		StringBuilder returnString = new StringBuilder();
		returnString.append("Rental Record for Customer Name" + RETORNO_CARRO);
		for (Rental aRental : this.customer.getRentals()) {
			returnString.append(BARRA_T + aRental.getMovie().getTitle());
			returnString.append(BARRA_T + String.valueOf(aRental.getCharge()) + RETORNO_CARRO);
		}
		returnString.append("Amount owed is " + String.valueOf(amountOwed) + RETORNO_CARRO + "You earned "
				+ String.valueOf(amountEarned) + " frequent renter points");
		return returnString.toString();
	}

	private String htmlStringToCompare(double amountOwed, int amountEarned) {
		StringBuilder returnString = new StringBuilder();
		returnString.append("<H1>Rentals for <EM>" + this.customer.getName() + "</EM></H1><P>" + RETORNO_CARRO);
		for (Rental aRental : this.customer.getRentals()) {
			returnString.append(aRental.getMovie().getTitle() + ": " + String.valueOf(aRental.getCharge()) + "<BR>\n");
		}
		returnString.append("<P>You owe <EM>" + String.valueOf(amountOwed) + "</EM><P>\n");
		returnString.append(
				"On this rental you earned <EM>" + String.valueOf(amountEarned) + "</EM> frequent renter points<P>");
		return returnString.toString();
	}

	private Rental generateRental(Movie movie, int daysRented) {
		Rental aRental = new Rental(movie, daysRented);
		return aRental;
	}

	private Movie generateMovieType(int movie) {
		return new Movie("First Film", movie);
	}

}
