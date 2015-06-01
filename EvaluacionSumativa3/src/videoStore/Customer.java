package videoStore;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector _rentals	= new Vector();
	
	public Customer(String name)
	{
		_name=name;
	}
	
	public void addRental(Rental arg)
	{
		_rentals.addElement(arg);
	}
	
	public String getName()
	{
		return _name;
	}
	
	
	public String statement()
	{
		double totalAmount			= 0;
		int frequentRenterPoints	= 0;
		Iterator it					= _rentals.iterator();
		String result				= "Rental Record for " + getName()+"\n";
		
		while (it.hasNext())
		{
			double thisAmount	= 0;
			Rental each			= (Rental) it.next();
			
			//determine amounts for each line
			thisAmount = getAmount(thisAmount, each);
			
			frequentRenterPoints = getFrequentRenterPoints(
					frequentRenterPoints, each);
			
			//show figures for this rental
			result+= "\t" + each.getMovie().getTitle() + "\t"+String.valueOf(thisAmount) + "\n";
			
			totalAmount +=thisAmount;
		}
		
		result = addFooterLines(totalAmount, frequentRenterPoints, result);
		
		return result;
		
	}

	public int getFrequentRenterPoints(int frequentRenterPoints, Rental each) {
		//add frequent renter points
		frequentRenterPoints++;
		
		//add bonus for a two day new release rental
		if ((each.getMovie().getPriceCode()==Movie.NEW_RELEASE) && each.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	public double getAmount(double thisAmount, Rental each) {
		switch (each.getMovie().getPriceCode())
		{
			case Movie.REGULAR: 
				thisAmount += 2;
				if (each.getDaysRented() > 2)
					thisAmount += (each.getDaysRented()-2) * 1.5;
				break;
			
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
				
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (each.getDaysRented()>3)
					thisAmount += (each.getDaysRented()-3) * 1.5;
				break;
		}
		return thisAmount;
	}

	private String addFooterLines(double totalAmount, int frequentRenterPoints,
			String result) {
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "Your earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}
