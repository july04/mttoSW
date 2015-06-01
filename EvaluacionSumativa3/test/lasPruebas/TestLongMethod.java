package lasPruebas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import videoStore.Customer;
import videoStore.Movie;
import videoStore.Rental;

public class TestLongMethod {

	Customer 	customer;
	Movie 		movie1, movie2;
	Rental 		rental1, rental2;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer("Julio Gastelum");
		customer.addRental(new Rental(new Movie("The Avengers", Movie.REGULAR), 3));
		customer.addRental(new Rental(new Movie("Dragon Ball Z: Rebirth of F", Movie.CHILDRENS), 2));
		
		System.out.println(customer.statement());
	}

	@Test
	public void testStatement() {
		String resultado = "Rental Record for Julio Gastelum\n" +
				"\t" + "The Avengers	3.5\n" +
				"\t" + "Dragon Ball Z: Rebirth of F	1.5\n" +
				"Amount owed is 5.0\n" +
				"Your earned 2 frequent renter points";
		Assert.assertTrue(resultado.equals(customer.statement()));
	}

}
