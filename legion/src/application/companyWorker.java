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
 * Класс-соединение с БД. Содержит в себе соединения с БД по вопросам таблицы спортсменов
 */
public class companyWorker {

	public static void add_sportsman(String name, String surname, String lastname, int telephone, int age, int weight) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

			String query = "INSERT INTO Sportsman (`name`, `surname`, `lastname`, `telephone`, `age`, `weight`) "
					+ " VALUES ('" + name + "', '" + surname + "', '" + lastname + "', " + telephone + ",  " + age
					+ ", " + weight + ");";
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

	public static void delete_sportsman(int id) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
			String sqlDelete = "delete from Sportsman where idSportsman = " + id;
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

	public static void update_sportsman(int idSportsman, String name, String surname, String lastname, int telephone,
			int age, int weight) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

			String strUpdate = "update Sportsman set name = '" + name + "', surname = '" + surname + "', lastname = '"
					+ lastname + "', telephone = " + telephone + ", age = '" + age + "', weight = " + weight
					+ " where idSportsman = " + idSportsman + "";
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

	public static List<Sportsman> show_sportabon_list() {
		List<Sportsman> list = new ArrayList<Sportsman>();
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

			String sql = "SELECT * FROM Sportsman";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int idSportsman = rs.getInt("idSportsman");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String lastname = rs.getString("lastname");
				int telephone = rs.getInt("telephone");
				int age = rs.getInt("age");
				int weight = rs.getInt("weight");
				list.add(new Sportsman(idSportsman, name, surname, lastname, telephone, age, weight));
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

	public static List<Sportsman> show_sportwinner_list(int ID) {
		List<Sportsman> list = new ArrayList<Sportsman>();
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

			String sql = "SELECT * FROM Sportsman, Competition_has_Sportsman where Competition_idCompetition = '" + ID
					+ "' and Sportsman_idSportsman = idSportsman;";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int idSportsman = rs.getInt("idSportsman");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String lastname = rs.getString("lastname");
				int telephone = rs.getInt("telephone");
				int age = rs.getInt("age");
				int weight = rs.getInt("weight");
				list.add(new Sportsman(idSportsman, name, surname, lastname, telephone, age, weight));
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
}
