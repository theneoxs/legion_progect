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
 * Класс-соединение с БД. Содержит в себе соединения с БД по вопросам таблицы тренеров
 */
public class individualContract {
	public static List<Coach> show_coach_list() {
		List<Coach> list = new ArrayList<Coach>();
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

			String sql = "SELECT idCoach, name, surname, lastname, telephone, age, `rank` FROM Coach";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int idCoach = rs.getInt("idCoach");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String lastname = rs.getString("lastname");
				int telephone = rs.getInt("telephone");
				int age = rs.getInt("age");
				String rank = rs.getString("rank");
				list.add(new Coach(idCoach, name, surname, lastname, telephone, age, rank));
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
	public static Coach show_coach_concr(int id) {
		Coach list = null;
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

			String sql = "SELECT * FROM Coach where idCoach = '"+id+"';";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int idCoach = rs.getInt("idCoach");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String lastname = rs.getString("lastname");
				int telephone = rs.getInt("telephone");
				int age = rs.getInt("age");
				String rank = rs.getString("rank");
				list = new Coach(idCoach, name, surname, lastname, telephone, age, rank);
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
	public static void add_coach(String name, String surname, String lastname, int telephone, int age, String rank) {
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

			String query = "INSERT INTO Coach (name, surname, lastname, telephone, age, `rank`)" + " VALUES ('" + name + "', '" + surname + "', '"
					+ lastname + "', " + telephone + ", " + age + ", '" + rank + "');";
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

	public static void delete_coach(int id) {

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

			String sqlDelete = "delete from Coach where idCoach = " + id;
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

	public static void update_user(int id, String name, String surname, String lastname, int telephone, int age, String rank) {

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
			
			String strUpdate = "update Coach set name = '" + name + "', surname = '" + surname + "', lastname = '"
					+ lastname + "', telephone = " + telephone + ", age = " + age + ", `rank` = '" + rank
					+ "' where idCoach = " + id + "";
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
