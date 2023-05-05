import java.sql.SQLException;
import java.util.Scanner;
public class BusDemo {
	public static void main(String[] args){
		BusDAO busdao = new BusDAO();
		try {
			busdao.displayBusInfo();
			int no=1;
			Scanner sc=new Scanner(System.in);
			while(no!=2) {
				System.out.println("Enter 1 to Booking and 2 to Exit");
				no=sc.nextInt();
				if(no==1) {
					Booking bk=new Booking();
					if(bk.isAvailable()) {
						BookingDAO bookingdao = new BookingDAO();
						bookingdao.addBooking(bk);
						System.out.println("Booking confirmed");
					}
					else {
						System.out.println("Bus Not Available");
					}
				}
			}
			sc.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
	}
}
