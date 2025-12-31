package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
//			st = conn.prepareStatement(
//					"INSERT INTO seller "
//					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
//					+ "VALUES "
//					+ "(?, ?, ?, ?, ?)",
//					Statement.RETURN_GENERATED_KEYS);
//			
//			st.setString(1, "Steve Rogers");
//			st.setString(2, "steverogersAvengers@gamil.com");
//			st.setDate(3, new java.sql.Date(sdf.parse("04/07/1918").getTime()));
//			st.setDouble(4, 3000.00);
//			st.setInt(5, 4);
			
			st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {				
				ResultSet rs = st.getGeneratedKeys();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! id = " + id);
				}
				System.out.println("Done! Rows affected: " + rowsAffected);
			} else {
				System.out.println("No rows affected!");
			}
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
