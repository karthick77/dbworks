package busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BookingDAO {

	public int getBookCount(int bus_no, Date travel_date) throws SQLException {
		String bookingCount = "select count(passanger_name) from booking where bus_no=? and travel_date=?";
		Connection dbInstance = DbConnection.getDBInstance();

		java.sql.Date sqlDate = new java.sql.Date(travel_date.getTime());

		PreparedStatement pst = dbInstance.prepareStatement(bookingCount);
		pst.setInt(1, bus_no);
		pst.setDate(2, sqlDate);
		ResultSet rs = pst.executeQuery();

		rs.next();

		return rs.getInt(1);
	}

	public void addBooking(BookingModal booking) throws SQLException {

		String bookingCount = "insert into booking values(?,?,?)";
		Connection dbInstance = DbConnection.getDBInstance();

		PreparedStatement pst = dbInstance.prepareStatement(bookingCount);
		pst.setString(1, booking.passanger_name);
		pst.setInt(2, booking.bus_no);
		java.sql.Date sqlDate = new java.sql.Date(booking.travel_date.getTime());
		pst.setDate(3, sqlDate);

		pst.executeUpdate();


	}



}
