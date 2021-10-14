/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenmax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Akash
 */
public class Database {
    public int code;

	public String fname;
	
	public int reg;
	

	
        Connection conn;
    
   
    public void insert(String name,String username,String password,String dob) 
    {
       
        try {
            //FileInputStream fin=new FileInputStream();
            Class.forName("oracle.jdbc.driver.OracleDriver");
             this.conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","akash","tiger");
            PreparedStatement pst = conn.prepareStatement("insert into user_information values (?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, dob);
           int i=pst.executeUpdate(); 
         
            System.out.println(i+" records inserted");  
            pst.close();
            conn.close();  
  
       }catch(Exception e){ System.out.println(e);}  
  
        }
    
    
	public boolean init() throws SQLException {
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");

			try {
				 this.conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","akash","tiger");
          
			} catch (SQLException e) {

				System.out.println("Error: Database Connection Failed ! Please check the connection Setting");

				return false;

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

			return false;
		}

		return true;
	}

	public void insert1() {
		String sql = "INSERT INTO face_bio (code, first_name, reg) VALUES (?, ?, ?)";

		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			statement.setInt(1, this.code);
			statement.setString(2, this.fname);

			
			statement.setInt(4, this.reg);
			

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new face data was inserted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> getUser(int inCode) throws SQLException {

		ArrayList<String> user = new ArrayList<String>();

		try {

			Database app = new Database();

			String sql = "select * from face_bio where code=" + inCode + " limit 1";

			Statement s = conn.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				
				user.add(0, Integer.toString(rs.getInt(2)));
				user.add(1, rs.getString(3));
				
				user.add(3, Integer.toString(rs.getInt(5)));
				

				/*
				 * System.out.println(app.getCode());
				 * System.out.println(app.getFname());
				 * System.out.println(app.getLname());
				 * System.out.println(app.getReg());
				 * System.out.println(app.getAge());
				 * System.out.println(app.getSec());
				 */

				// nString="Name:" + rs.getString(3)+" "+rs.getString(4) +
				// "\nReg:" + app.getReg() +"\nAge:"+app.getAge() +"\nSection:"
				// +app.getSec() ;

				// System.out.println(nString);
			}

			conn.close(); // closing connection
		} catch (Exception e) {
			e.getStackTrace();
		}
		return user;
	}

	public void db_close() throws SQLException
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	
	

	public int getReg() {
		return reg;
	}

	public void setReg(int reg) {
		this.reg = reg;
	}


       
}
