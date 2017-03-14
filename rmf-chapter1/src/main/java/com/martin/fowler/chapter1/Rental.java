package com.martin.fowler.chapter1;

public class Rental {

	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.setMovie(movie);
		this.setDaysRented(daysRented);
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public double getCharge() {
		return getMovie().getCharge(getDaysRented());
	}

	public int getFrequentRenterPoints() {
		return movie.getFrequentRenterPoints(daysRented);
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @param daysRented the daysRented to set
	 */
	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

}
