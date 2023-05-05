import java.sql.*;
public class BusDAO {
	
	public void displayBusInfo() throws SQLException {
		String query = "select * from bus;";
		Connection con = DBConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			System.out.println("Bus No: "+rs.getInt(1)+"		Ac/Non-Ac: "+rs.getBoolean(2)+"			Capacity: "+rs.getInt(3));
		}
	}
	
	public int getCapacity(int bno) throws SQLException{
		String query = "select capacity from bus where busnum="+bno;
		Connection con = DBConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
		
	}
}
