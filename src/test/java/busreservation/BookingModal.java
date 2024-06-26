package busreservation;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class BookingModal {

	String passanger_name;
	int bus_no;
	Date travel_date;

	public BookingModal(){

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		String currentDate = dtf.format(now);

		Scanner scBook = new Scanner(System.in);
		System.out.println("Enter name of passangar: ");
		passanger_name = scBook.next();
		System.out.println("Enter Bus No to book: ");
		bus_no = scBook.nextInt();
		System.out.println("Enter date of birth dd-mm-yyyy");
		String tdate = scBook.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try {
			travel_date = dateFormat.parse(tdate);
			

		} catch (ParseException e) {
			e.printStackTrace();
		}


	}

	public boolean isAvailable() throws SQLException {

		BusDAO busDAO = new BusDAO();
		BookingDAO bookDAO = new BookingDAO();
		int capacity = busDAO.getCapacity(bus_no);
		int booking = bookDAO.getBookCount(bus_no, travel_date);

		return booking<capacity;
	}



}
