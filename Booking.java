import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Booking {
	String name;
	int bno;
	Date date;
	
	Booking(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the passenger name: ");
		name=sc.next();
		System.out.println("Enter the Bus Number: ");
		bno=sc.nextInt();
		System.out.println("Enter the Traveling Date: dd-mm-yyyy");
		String dateInput=sc.next();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		try {
			date=sdf.parse(dateInput);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
	}
	public boolean isAvailable() throws SQLException {
		BusDAO bsd = new BusDAO();
		BookingDAO bkd = new BookingDAO();
		int capacity=bsd.getCapacity(bno);
		int booked=bkd.getBookedCount(bno, date);
		return booked<capacity;
	}
}
