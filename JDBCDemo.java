import java.sql.*;
public class JDBCDemo {

	public static void main(String[] args) throws SQLException {
		batchdemo();
	}
	public static void readrecord() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		String query = "select * from employee";
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
		System.out.println("Eid = "+rs.getInt(1));
		System.out.println("Ename = "+rs.getString(2));
		System.out.println("Esalary = "+rs.getString(3));
		}
		con.close();
	}
	public static void insertrecord() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		String query = "insert into employee values(4,\"varun\", 40000)";
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		System.out.println(rows);
		con.close();
	}
	public static void insertvar() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=6;
		String name="tamil";
		int salary=650000;
		String query = "insert into employee values(" + id + ", '" + name + "', " + salary + ");";
		//System.out.print(query);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		System.out.println(rows);
		con.close();
	}
	public static void insertps() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=7;
		String name="sarvesh";
		int salary=550000;
		String query = "insert into employee values(?,?,?);";
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setInt(3, salary);
		int rows = pst.executeUpdate();
		System.out.println(rows);
		con.close();
	}
	public static void delete() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=6;
		String name="sarvesh";
		int salary=550000;
		String query = "delete from employee where emp_id = ?;";
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		int rows = pst.executeUpdate();
		System.out.println("Number of rows affected "+rows);
		con.close();
	}
	public static void update() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=4;
		String name="sarvesh";
		int salary=550000;
		String query = "update employee set salary = 500000 where emp_id = ?;";
		Connection con = DriverManager.getConnection(url, user, password);
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		int rows = pst.executeUpdate();
		System.out.println("Number of rows affected "+rows);
		con.close();
	}
	public static void sp() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		String query = "{call getbranches()};";
		Connection con = DriverManager.getConnection(url, user, password);
		CallableStatement cst = con.prepareCall(query);
		ResultSet rs = cst.executeQuery();
		while(rs.next()) {
			System.out.println("Eid = "+rs.getInt(1));
			System.out.println("Ename = "+rs.getString(2));
			System.out.println("Esalary = "+rs.getString(3));
			}
		con.close();
	}
	public static void sp2() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=3;
		String query = "{call GetEmpById(?)};";
		Connection con = DriverManager.getConnection(url, user, password);
		CallableStatement cst = con.prepareCall(query);
		cst.setInt(1, id);
		ResultSet rs = cst.executeQuery();
		while(rs.next()) {
			System.out.println("Eid = "+rs.getInt(1));
			System.out.println("Ename = "+rs.getString(2));
			System.out.println("Esalary = "+rs.getString(3));
			}
		con.close();
	}
	public static void sp3() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=3;
		String query = "{call GetNameById(?,?)};";
		Connection con = DriverManager.getConnection(url, user, password);
		CallableStatement cst = con.prepareCall(query);
		cst.setInt(1, id);
		cst.registerOutParameter(2, Types.VARCHAR);
		cst.executeUpdate();
		System.out.println(cst.getString(2));
		con.close();
	}
	public static void commitdemo() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=3;
		String query1 = "update employee set salary = 500000 where emp_id = 1;";
		String query2 = "update employee set salary = 500000 where emp_id = 2;";
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		con.setAutoCommit(false);
		int row1 = st.executeUpdate(query1);
		int row2 = st.executeUpdate(query2);
		System.out.println("Rows affected "+row1);
		System.out.println("Rows affected "+row2);
		if(row1>0 && row2>0)
			con.commit();
		con.close();
	}
	public static void batchdemo() throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String user="root";
		String password="Jotheesh@2001";
		int id=3;
		String query1 = "update employee set salary = 800000 where emp_id = 1;";
		String query2 = "update employee set salary = 800000 where emp_id = 2;";
		String query3 = "update employee set salary = 800000 where emp_id = 3;";
		String query4 = "update employee set salary = 800000 where emp_id = 4;";
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		con.setAutoCommit(false);
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
		st.addBatch(query4);
		int[] res = st.executeBatch();
		for(int x:res) {
			if(x>0)
				continue;
			else
				con.rollback();
		}
		con.commit();
		con.close();
	}
	
}
