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
 * Класс-соединение с БД. Содержит в себе соединения с БД по вопросам таблицы групповых тренировок
 */
public class Group_has_Train {
	public static List<Training> show_train_list(){
		List<Training> list = new ArrayList<Training>();
		Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC", Main.login, Main.password);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Group_has_train, Train where Train.idTrain = Group_has_train.Train_idTrain;";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int Group_idGroup = rs.getInt("Group_idGroup");
                String date = rs.getString("date");
                int Train_idTrain = rs.getInt("Train_idTrain");
                String time = rs.getString("time");
                int idTrain = rs.getInt("idTrain");
                String name = rs.getString("name");
                String description = rs.getString("description");
                list.add(new Training(Group_idGroup, date, Train_idTrain, time, idTrain, name, description));
                System.out.println(name);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return list;
    }
	
    public static void add_group_has_train(int Group_idGroup, int Train_idTrain, String date, String time){

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC", Main.login, Main.password);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String query = "INSERT INTO Group_has_Train " + " VALUES ("+Group_idGroup+", "+Train_idTrain+", '"+date+"', '"+time+"');";
            stmt.executeUpdate(query);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    public static void delete_group_has_train(int Group_idGroup, int Train_idTrain, String date, String time){
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC", Main.login, Main.password);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sqlDelete = "delete from Group_has_Train where Group_idGroup = '"+Group_idGroup+"', Train_idTrain = '"+Train_idTrain+"', date = '"+date+"', time = '"+time+"';";
            stmt.executeUpdate(sqlDelete);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    public static void update_group_has_train(int Group_idGroup, int Train_idTrain, String date, String time){

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&serverTimezone=UTC", Main.login, Main.password);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String strUpdate = "update Group_has_Train set Train_idTrain = "+Train_idTrain+", date = '"+date+"', time = '"+time+"' where Group_idGroup = "+Group_idGroup+"";
            stmt.executeUpdate(strUpdate);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

    }

}
