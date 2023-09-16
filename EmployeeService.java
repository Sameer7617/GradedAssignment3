package com.gl.service;

import java.util.ArrayList;

import com.gl.Dao.EmployeeImpl;
import com.gl.bean.Employee;

public class EmployeeService {
	EmployeeImpl ser=new EmployeeImpl();
	
	public EmployeeService() {
		EmployeeImpl ser=new EmployeeImpl();
	}

	public boolean createTable() {
			
			return ser.createTable();
		}
	public boolean insertEmployee(Employee in) {
			return ser.insertEmployee(in);
		}
	public boolean updateEmployee(Employee up) {
			return ser.updateEmployee(up);
		}
	public boolean deleteEmployeeById(int Id) {
			return ser.deleteEmployeeById(Id);
		}
	public Employee getEmployeeById(int Id) {
			return ser.getEmployeeById(Id);
		}
	public ArrayList <Employee> getAllEmployees(){
		ArrayList <Employee> emps =new ArrayList <Employee>();
		emps=ser.getAllEmployees();
		return emps;
		}
		public boolean alterEmailColumn() {
			return ser.alterEmailColumn();
		}
		public ArrayList <Integer> getEmployeeIds(){
			ArrayList <Integer> emps1 =new ArrayList <Integer>();
			emps1=ser.getEmployeeIds();
			return emps1;
			
		}
		public boolean removeAllRecords()
		{
			return ser.removeAllRecords();
		}

		
}