package com.gl.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gl.bean.Employee;
import com.gl.connection.DBConnection;

public class EmployeeImpl implements EmployeeDAO {
	PreparedStatement pstmt=null;
	DBConnection dbcon=null;
	ResultSet st=null;
	Statement stmt=null;
	Connection con=null;
	Employee emp=null;
	ArrayList<Employee> emps=null;
	
	
	
	public EmployeeImpl() {
		 emp=new Employee();
		 emps=new ArrayList<Employee>();	
		 dbcon=new DBConnection();
		
	}

	@Override
	public boolean createTable() {
		con=dbcon.getConnection();
		try {
		stmt=con.createStatement();	
		
		int ct=stmt.executeUpdate("create table employees (Id int primary key, "
				+ "Name varchar(50) not null, Email_Id varchar(50) not null, Phone_Number Varchar(12) not null)");	
		return true;	
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertEmployee(Employee in) {
		con=dbcon.getConnection();
		
		try {
			String q="insert into employees values (?,?,?,?)";
			pstmt=con.prepareStatement(q);
			pstmt.setInt(1,in.getId());
			pstmt.setString(2, in.getName());
			pstmt.setString(3, in.getEmail_Id());
			pstmt.setString(4, in.getPhone_Number());
			pstmt.executeUpdate();
			return true;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateEmployee(Employee up) {
		con=dbcon.getConnection();
		String  q="update employees name=?,email_id=?,phone_number=? where id=?";
		try {
			pstmt=con.prepareStatement("q");
			pstmt.setString(1,up.getName() );
			pstmt.setString(2,up.getEmail_Id() );
			pstmt.setString(3, up.getPhone_Number());
			pstmt.setInt(4, up.getId());
			pstmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteEmployeeById(int Id) {
		con=dbcon.getConnection();
		
		try {
			String q="delete from employees where id =?";
		
		    pstmt=con.prepareStatement(q);
			pstmt.setInt(1, Id);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee getEmployeeById(int Id) {
		con=dbcon.getConnection();
		emp=new Employee();
		try {
		 
			String q="select * from employees where id=?";	
			pstmt=con.prepareStatement("q");
			pstmt.setInt(1, Id);
			st=pstmt.executeQuery();
			while(st.next()) {
			emp.setId(st.getInt(1));
			emp.setName("st.getString(2)");
			emp.setEmail_Id("st.getString(3)");
			emp.setPhone_Number("st.getString(4)");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	
		return emp;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		con =dbcon.getConnection();
		emps=new ArrayList<Employee>();
		
		try {
			String q="select * from employees ";
			stmt=con.createStatement();
			st=stmt.executeQuery(q);
			while(st.next()) {
				Employee e=new Employee();
				e.setId(st.getInt(1));
				e.setName("st.getString(2)");
				e.setEmail_Id("st.getString(3)");
				e.setPhone_Number("st.getString(4)");
				emps.add(e);
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return emps;
	}

	@Override
	public boolean alterEmailColumn() {
		con=dbcon.getConnection();
		
		try {
			stmt=con.createStatement();
			int rs = stmt.executeUpdate("alter table Employees modify column Email_Id varchar(30)");
			System.out.println(rs+" rows updated");
			return true;
					} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public ArrayList<Integer> getEmployeeIds() {
	con=dbcon.getConnection();
	 ArrayList<Integer> empA=new  ArrayList<Integer>();
	 String q="select id from employees";
	 try {
		stmt=con.createStatement();
		st=stmt.executeQuery("q");
		while(st.next()) {
			int eid=st.getInt(1);
			empA.add(eid);
			
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
		return empA;
	}
	@Override
	public boolean removeAllRecords()
	{
	con=dbcon.getConnection();
	try {
		String q="Truncate table employees";
		stmt=con.createStatement();
		stmt.executeUpdate(q);
		return true;
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
		return false ;
	}
}