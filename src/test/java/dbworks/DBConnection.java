package dbworks;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DBConnection {

	/*
	 * Driver and Database: jdbc:mysql:
	 * Server Name: localhost
	 * Port Number: 3306
	 * Databasename: jdbcdemo
	 * Username: root
	 * Password: Tarakpwd77
	 */

	public static void readRecords() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();

			String query = "select * from jdbcdemo.employee;";

			ResultSet rs = createStatement.executeQuery(query);

			while(rs.next()) {
				System.out.println("Employee ID is: " +rs.getInt(1));
				System.out.println("Employee Name: " +rs.getString(2));
				System.out.println("Salary: " +rs.getInt(3));
				System.out.println("=======================================");
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void insertRecords() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();

			String query = "insert into employee values(8,'Vidhya',70000);";

			int rows = createStatement.executeUpdate(query);
			System.out.println("Number of rows affected: "+rows);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertRecordsWithVariables() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();

			int id=9;
			String name = "Tarun";
			int salary = 82000;

			String query = "insert into employee values("+id+",'"+name+"',"+salary+");";

			int rows = createStatement.executeUpdate(query);
			System.out.println("Number of rows affected: "+rows);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertRecordsUsingPreparedStatement() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");

			int id = 11;
			String name = "Vasu";
			int salary = 90000;
			String query = "insert into employee values(?,?,?);";

			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setInt(3, salary);
			int executeUpdate = pst.executeUpdate();

			System.out.println("Number of rows affected: "+executeUpdate);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteARecord() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");

			int id = 11;
			String query = "delete from employee where emp_id =(?);";

			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, id);
			int executeUpdate = pst.executeUpdate();

			System.out.println("Number of rows affected: "+executeUpdate);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateARecord() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");

			int id = 10;
			int salary = 105000;
			String query = "update employee set salary=(?) where emp_id=(?);";

			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, salary);
			pst.setInt(2, id);
			int executeUpdate = pst.executeUpdate();

			System.out.println("Number of rows affected: "+executeUpdate);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void callableStatement() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			CallableStatement prepareCall = connection.prepareCall("{call jdbcdemo.GetEmp()}");
			ResultSet rs = prepareCall.executeQuery();
			
			while(rs.next()) {
				System.out.println("Employee ID is: " +rs.getInt(1));
				System.out.println("Employee Name: " +rs.getString(2));
				System.out.println("Salary: " +rs.getInt(3));
				System.out.println("=======================================");
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callableStatementWithId() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			int id=3;
			CallableStatement prepareCall = connection.prepareCall("{call jdbcdemo.GetEmpById(?)}");
			prepareCall.setInt(1, id);
			ResultSet rs = prepareCall.executeQuery();
			
			while(rs.next()) {
				System.out.println("Employee ID is: " +rs.getInt(1));
				System.out.println("Employee Name: " +rs.getString(2));
				System.out.println("Salary: " +rs.getInt(3));
				System.out.println("=======================================");
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void callableStatementWithGetName() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			int id=3;
			CallableStatement prepareCall = connection.prepareCall("{call jdbcdemo.GetNameById(?,?)}");
			prepareCall.setInt(1, id);
			prepareCall.registerOutParameter(2, Types.VARCHAR);
			prepareCall.executeUpdate();
			
			System.out.println(prepareCall.getString(2));
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commitVsAutocommit() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();
			String query1 = "update employee set salary=400000 where emp_id=1";
			String query2 = "update employee set salary=400000 where emp_id=2";
			int res1 = createStatement.executeUpdate(query1);
			int res2 = createStatement.executeUpdate(query2);
			
			System.out.println(res1);
			System.out.println(res2);
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commitVsAutocommitAll() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();
			connection.setAutoCommit(false);
			String query1 = "update employee set salary=410000 where emp_id=3";
			String query2 = "update employee set salary=450000 where emp_id=4";
			int res1 = createStatement.executeUpdate(query1);
			int res2 = createStatement.executeUpdate(query2);
			
			System.out.println(res1);
			System.out.println(res2);
			
			if(res1>0 && res2>0) {
				connection.commit();
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void batchRun() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();
			
			String query1 = "update employee set salary=400000 where emp_id=3";
			String query2 = "update employee set salary=400000 where emp_id=4";
			String query3 = "update employee set salary=400000 where emp_id=5";
			String query4 = "update employee set salary=400000 where emp_id=6";
			
			createStatement.addBatch(query4);
			createStatement.addBatch(query3);
			createStatement.addBatch(query2);
			createStatement.addBatch(query1);
			
			int[] executeBatch = createStatement.executeBatch();
			
			for (int i : executeBatch) {
				System.out.println(i);
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void batchRunWithRollBack() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql:///jdbcdemo","root","Tarakpwd77");
			Statement createStatement = connection.createStatement();
			
			String query1 = "update employee set salary=360000 where emp_id=7";
			String query2 = "update employee set salary=360000 where emp_id=8";
			String query3 = "update employee set salary=380000 where emp_id=9";
			String query4 = "update employee set salary=380000 where emp_id=10";
			
			createStatement.addBatch(query4);
			createStatement.addBatch(query3);
			createStatement.addBatch(query2);
			createStatement.addBatch(query1);
			
			int[] executeBatch = createStatement.executeBatch();
			
			for (int i : executeBatch) {
				if(i>0) {
					continue;
				}else {
					connection.rollback();
				}
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		readRecords();
		//insertRecords();
		//insertRecordsWithVariables();
		//insertRecordsUsingPreparedStatement();
		//deleteARecord();
		//updateARecord();
		//callableStatement();
		//callableStatementWithId();
		//callableStatementWithGetName();
		//commitVsAutocommit();
		//commitVsAutocommitAll();
		//batchRun();
		//batchRunWithRollBack();
	}



}
