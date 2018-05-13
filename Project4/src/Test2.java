import java.util.*;
import javax.jdo.*;
import com.objectdb.Utilities;

public class Test2
{
    public static void main(String argv[])
    {
    {
        PersistenceManager pm = Utilities.getPersistenceManager( "flight2.odb" );

        System.out.println( "-- TEST Flight.find --\n" );
        Flight f585 = Flight.find( "Delta", "585", pm );
        Flight f655 = Flight.find( "Delta", "655", pm );
        Flight f300A = Flight.find( "United", "300A", pm );
        Flight f351 = Flight.find( "American", "351", pm );

        System.out.println( f585 );
        System.out.println( f655 );
        System.out.println( f300A );
        System.out.println( f351 );
        System.out.println();

        System.out.println( "-- TEST Time.differenceFrom --\n" );
        System.out.println( f655.departTime.differenceFrom(f585.arriveTime) );
        System.out.println( f351.departTime.differenceFrom(f300A.arriveTime) );
        System.out.println( f585.arriveTime.differenceFrom(f655.departTime) );
        System.out.println( f300A.arriveTime.differenceFrom(f351.departTime) );
        System.out.println();

        System.out.println( "-- TEST Time.isInInterval --\n" );
        System.out.println( f585.departTime.isInInterval(20,0,0,30) );
        System.out.println( f585.departTime.isInInterval(19,30,2,30) );
        System.out.println( f585.departTime.isInInterval(20,30,0,30) );
        System.out.println( f585.departTime.isInInterval(8,30,14,40) );
        System.out.println();

        System.out.println( "-- TEST Flight.getFlights: New York La Guardia to Chicago O'Hare --\n" );
        Query q = pm.newQuery();
        Collection<Flight> ff = Flight.getFlights( "New York La Guardia", "Chicago O'Hare", q );
        Utility.printCollection( ff );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.getFlights: St. Louis Lambert to Los Angeles LAX --\n" );
        q = pm.newQuery();
        ff = Flight.getFlights( "St. Louis Lambert", "Los Angeles LAX", q );
        Utility.printCollection( ff );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.getFlightsForCities: Riverside to Flushing --\n" );
        q = pm.newQuery();
        ff = Flight.getFlightsForCities( "Riverside", "Flushing", q );
        Utility.printCollection( ff );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.getFlightsDepartTime: Chicago O'Hare to Washington Dulles --\n" );
        q = pm.newQuery();
        ff = Flight.getFlightsDepartTime( "Chicago O'Hare", "Washington Dulles", 9, 30, 11, 30, q );
        Utility.printCollection( ff );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.getFlightsDepartTime: New York JFK to Washington Dulles --\n" );
        q = pm.newQuery();
        ff = Flight.getFlightsDepartTime( "New York JFK", "Washington Dulles", 19, 30, 1, 30, q );
        Utility.printCollection( ff );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.getFlightsConnection: New York JFK to Los Angeles LAX --\n" );
        q = pm.newQuery();
        Collection<Object[]> tuples = Flight.getFlightsConnection( "New York JFK", "Los Angeles LAX", 19, 30, 21, 30, 83, 115, q );
        Utility.printCollectionOfArrays( tuples );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.getFlightsConnection: Chicago O'Hare to Los Angeles LAX --\n" );
        q = pm.newQuery();
        tuples = Flight.getFlightsConnection( "Chicago O'Hare", "Los Angeles LAX", 9, 45, 11, 0, 60, 90, q );
        Utility.printCollectionOfArrays( tuples );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Airport.airportsForCompany: American --\n" );
        q = pm.newQuery();
        Collection<Airport> aa = Airport.airportsForCompany( "American", q );
        Utility.printCollection( aa );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Airport.airportsForCompany: Delta --\n" );
        q = pm.newQuery();
        aa = Airport.airportsForCompany( "Delta", q );
        Utility.printCollection( aa );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Airport.airportsForCompany: United --\n" );
        q = pm.newQuery();
        aa = Airport.airportsForCompany( "United", q );
        Utility.printCollection( aa );
        q.closeAll();
        System.out.println();

        System.out.println( "-- TEST Flight.groupByCompany --\n" );
        q = pm.newQuery();
        tuples = Flight.groupByCompany(q);
        Utility.printCollectionOfArrays( tuples );
        q.closeAll();

        if ( !pm.isClosed() )
            pm.close();
    }
}