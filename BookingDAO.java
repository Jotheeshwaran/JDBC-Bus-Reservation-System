import java.sql.*;
import java.util.Date;
public class BookingDAO{
	public int getBookedCount(int bno,Date date) throws SQLException {
		String query = "select count(pname) from booking where bno=? and pdate=?";
		Connection con = DBConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1, bno);
		pst.setDate(2, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	public void addBooking(Booking booking) throws SQLException {
		String query = "insert into booking values(?,?,?)";
		Connection con = DBConnection.getConnection();
		java.sql.Date sqldate=new java.sql.Date(booking.date.getTime());
		PreparedStatement pst=con.prepareStatement(query);
		System.out.println(booking.name+" "+booking.bno+" "+sqldate);
		pst.setString(1, booking.name);
		pst.setInt(2, booking.bno);
		pst.setDate(3, sqldate);
		pst.executeUpdate();
	}
	
}