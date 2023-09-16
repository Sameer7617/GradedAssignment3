package com.gl.Dao;

import java.util.ArrayList;

import com.gl.bean.Employee;

public interface EmployeeDAO {
public boolean createTable();
public boolean insertEmployee(Employee in);
public boolean updateEmployee(Employee up);
public boolean deleteEmployeeById(int Id);
public Employee getEmployeeById(int Id);
public ArrayList <Employee> getAllEmployees();
public boolean alterEmailColumn();
public ArrayList <Integer> getEmployeeIds();
public boolean removeAllRecords();
	
	
	
	

}

