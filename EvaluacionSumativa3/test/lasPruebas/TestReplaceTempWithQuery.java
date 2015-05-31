package lasPruebas;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import videoStore.Customer;
import videoStore.Movie;
import videoStore.Rental;

public class TestReplaceTempWithQuery {

	Customer 	customer;
	Movie 		movie1, movie2;
	Rental 		rental1, rental2;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer("Jorge Petris");
		customer.addRental(new Rental(new Movie("Men of honor", Movie.REGULAR), 3));
		customer.addRental(new Rental(new Movie("Finding Nemo", Movie.CHILDRENS), 2));
		
		System.out.println(customer.statement());
		
		
	}

	@Test
	public void testStatement() {
		String resultado = "Rental Record for Jorge Petris\n" +
				"\t" + "Men of honor	3.5\n" +
				"\t" + "Finding Nemo	1.5\n" +
				"Amount owed is 5.0\n" +
				"Your earned 2 frequent renter points";
		Assert.assertTrue(resultado.equals(customer.statement()));
	}

}
