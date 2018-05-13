import java.util.*;
import javax.jdo.*;

@javax.jdo.annotations.PersistenceCapable

public class Airport
{
    String name; // key

    HashSet<City> closeTo = new HashSet<City>();

    // The set of cities close enough to this airport for their residents to use it;
    // Represents the inverse of City.closeTo

    HashSet<Flight> departingFlights = new HashSet<Flight>();

    // The set of flights departing from this airport;
    // Represents the inverse of Flight.origin

    HashSet<Flight> arrivingFlights = new HashSet<Flight>();

    // The set of flights arriving to this airport;
    // Represents the inverse of Flight.destination


    public String toString()
    {
        return name;
    }

    public static Collection<Airport> airportsForCompany(String airlineCompanyName, Query q)

	/* Returns the collection of all airports "a" such that there is at least one flight of 
	   the given airline company that departs from "a" or arrives to "a".
	   Sort the result by Airport.name. */

    {
        q.setClass(Airport.class);
        q.declareParameters("String airlineCompanyName");
        q.setFilter("this.arrivingFlights.name = airlineCompanyName,this.departingFlights.name = airlineCompanyName");
        q.setOrdering("this.name ascending");
        return (Collection<Airport>) q.execute(airlineCompanyName);

    }
}