package Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.gl.bean.Employee;
import com.gl.service.EmployeeService;

public class client {

	public static void main(String[] args) {
		int choice=0;
		EmployeeService empser=new EmployeeService();
		Scanner sc =new Scanner (System.in);
		
		do {
			System.out.println("............. EmployeeDetail Menu.........");
			System.out.println("1.Create table with given schema ");
			System.out.println("2.View All Employees");
			System.out.println("3.Search Employee By ID");
			System.out.println("4.Delete Employee ID");
			System.out.println("5.Update Employee");
			System.out.println("6.Alter column email to varchar 30");
			System.out.println("7.Remove all records from table");
			System.out.println("8.Insert Employee Record");
			System.out.println("9.Exit from EmployeeDetail Menu");
			System.out.println("Please enter Your Option...");
			choice=sc.nextInt();
			switch(choice){
			case 1:
			if(empser.createTable()) {
				System.out.println("Table Employees created Successfully");
			}
			else {
			System.out.println("Table creation failed...");
			}
			break;
			case 2:
				ArrayList<Employee> list=empser.getAllEmployees();
				Iterator it=list.iterator();
			while(it.hasNext()) {
				int itlist=(int) it.next();
				System.out.println(itlist);
			}
			break;
			case 3:
		    System.out.println("Enter id for which you want see the data of the table");
		    int id =sc.nextInt();
		    Employee emp2=empser.getEmployeeById(id);
		    System.out.println("the employee is"+emp2);
			case 4:
				System.out.println("Enter the id which you want delete from the table");
				int id1=sc.nextInt();
				empser.deleteEmployeeById(id1);
				break;
			case 5:
				System.out.println("Enter the id whic you want to update ");
				int idu=sc.nextInt();
				System.out.println("Enter the name which you want to update ");
				String str1=sc.next();
				System.out.println("Enter the email_id which you want to update ");
				String str2=sc.next();
				System.out.println("Enter the phone_number which you want to update ");
				String str3=sc.next();
				Employee up=new Employee(idu,"str1","str2","str3");
				empser.updateEmployee(up);
				break;
			case 6:
				empser. alterEmailColumn();
				break;
			case 7:
	
				 empser.removeAllRecords();
				break;
			case 8:
				System.out.println("Enter the id whic you want to insert ");
				int idi=sc.nextInt();
				System.out.println("Enter the name which you want to insert ");
				String str4=sc.next();
				System.out.println("Enter the email_id which you want to insert ");
				String str5=sc.next();
				System.out.println("Enter the phone_number which you want to insert ");
				String str6=sc.next();
				Employee in=new Employee();
				in.setId(idi);
				in.setName(str4);
				in.setEmail_Id(str5);
				in.setPhone_Number(str6);
				
				empser.insertEmployee(in);
				break;
					
			}			
			
		}while(choice!=9);
			
	}

}