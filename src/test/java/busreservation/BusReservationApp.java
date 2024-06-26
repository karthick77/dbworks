package busreservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class BusReservationApp {

	public static void main(String[] args) throws SQLException, IOException {

		String userOption = "b";
		InputStreamReader sc = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(sc);

		BusDAO busDAO = new BusDAO();
		busDAO.dispalyBusDetails();
		System.out.println("Enter 'b' for bookings or 'e' for exit");
		userOption = br.readLine();
		while(userOption.equalsIgnoreCase("b")) {

			if(userOption.equalsIgnoreCase("b")) {
				BookingModal booking = new BookingModal();
				if(booking.isAvailable()) {
					BookingDAO bookingDAO = new BookingDAO();
					bookingDAO.addBooking(booking);
					System.out.println("Your Booking is confirmed");
				}else {
					System.out.println("Sorry. Bus is full. Try another bus or date.");
				}
			}	

			System.out.println("Enter 'b' for bookings or 'e' for exit");
			userOption = br.readLine();
		} 
	}

}
