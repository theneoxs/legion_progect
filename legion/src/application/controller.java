package application;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * Класс-соединение с БД. Содержит в себе соединения с БД по вопросам таблицы абонементов
 */
public class controller {

	public static Abonement show_abon_list(int ID) {
		Abonement list = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC",
					Main.login, Main.password);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql = "SELECT idAbonement, date, lastDate, trainCount,Group_idGroup, Paytype_variant, Sportsman_idSportsman FROM Abonement where Sportsman_idSportsman = "
					+ ID + ";";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int idAbonement = rs.getInt("idAbonement");
				String date = rs.getString("date");
				String lastDate = rs.getString("lastDate");
				int trainCount = rs.getInt("trainCount");
				int Group_idGroup = rs.getInt("Group_idGroup");
				String Paytype_variant = rs.getString("Paytype_variant");
				int Sportsman_idSportsman = rs.getInt("Sportsman_idSportsman");
				list = new Abonement(idAbonement, date, lastDate, trainCount, Group_idGroup, Paytype_variant,
						Sportsman_idSportsman);
			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return list;
	}

	public static void add_abon(String date, String lastDate, int trainCount, int Group_idGroup, int idSportsman) {

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC",
					Main.login, Main.password);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String query = "INSERT INTO abonement (date, lastDate, trainCount, Group_idGroup, Paytype_variant, Sportsman_idSportsman) "
					+ " VALUES ('" + date + "', '" + lastDate + "', '" + trainCount + "', '" + Group_idGroup
					+ "', 'Card', " + idSportsman + ");";
			stmt.executeUpdate(query);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public static void delete_abon(int id) {

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC",
					Main.login, Main.password);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sqlDelete = "delete from Abonement where idAbonement = " + id;
			stmt.executeUpdate(sqlDelete);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public static void update_abon(int id, String date, String lastDate, int trainCount, int Group_idGroup, int idSportsman) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC",
					Main.login, Main.password);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
	
			String strUpdate = "update Abonement set date = '" + date + "', lastDate = '" + lastDate
					+ "', trainCount = '" + trainCount + "', Group_idGroup = '" + Group_idGroup
					+ "', Paytype_variant = 'Card', Sportsman_idSportsman = '"
					+ idSportsman + "' where idAbonement = " + id + "";
			stmt.executeUpdate(strUpdate);


		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}
	public static void pick(int id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC",
					Main.login, Main.password);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
	
			String strUpdate = "update Abonement set trainCount = trainCount-1 where idAbonement = " + id + "";
			stmt.executeUpdate(strUpdate);


		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}
}
