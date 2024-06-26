package busreservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BusDAO {
	
	public void dispalyBusDetails() throws SQLException {
		
		String busQuery = "select * from bus";
		Connection dbInstance = DbConnection.getDBInstance();
		Statement st = dbInstance.createStatement();
		ResultSet rs = st.executeQuery(busQuery);
		
		System.out.println("**********************BUS INFORMATION*****************");
		while(rs.next()) {
			System.out.print("Bus No: "+rs.getInt(1));
			if(rs.getInt(2) == 1) {
				System.out.print(" - 'A/C bus' - ");
			}else {
				System.out.print(" - 'Non A/C bus' - ");
			}
			System.out.print("Current Capacity: "+rs.getInt(3));
			System.out.println();
		}
		
		System.out.println("======================================================");
		
		
	}

	public int getCapacity(int bus_no) throws SQLException {
		

		String busCapacityQuery = "select capacity from bus where bus_no="+bus_no;
		Connection dbInstance = DbConnection.getDBInstance();
		Statement st = dbInstance.createStatement();
		ResultSet rs = st.executeQuery(busCapacityQuery);
		
		rs.next();
		int capacity = rs.getInt(1);
		
		return capacity;
		
	}
}
