package com.suppliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.sql.DataSource;

public class SuppliersDbUtil {
	private DataSource dataSource;
	public SuppliersDbUtil(DataSource theDataSource) {
		dataSource =  theDataSource;
	}
	public List<Suppliers> getSuppliers() throws Exception {
		List<Suppliers> Supplier = new ArrayList<>();
		//Set up JDBC
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
	try {
	// get a connection
myConn = dataSource.getConnection();		
	// create sql statement
String sql = "select * from spl_suppliers";
myStmt = myConn.createStatement();
	// execute query
myRs = myStmt.executeQuery(sql);		
	//process result set
while(myRs.next()) {
	//retrieve data from result set row
	int id = myRs.getInt("id");
	String firstName = myRs.getString("first_name");
	String lastName = myRs.getString("last_name");
	String phone = myRs.getString("phone");
	String location = myRs.getString("location");
	// create new student object
	Suppliers tempSuppliers = new Suppliers(id,firstName,lastName,phone,location);
	// add it to the list of students
Supplier.add(tempSuppliers);
}
return Supplier;
	}
	finally {
		//close JDBC objects
		close(myConn,myStmt,myRs);
	}
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addSupplier(Suppliers theSupplier) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;
	
	try {
	// get db connection
	myConn = dataSource.getConnection();
	//query to insert
	String sql ="insert into spl_suppliers"
			    + "(first_name,last_name,phone,location)"
			    + "values (?,?,?,?)";
	myStmt = myConn.prepareStatement(sql);
	
	//set the parameter for Suppliers
	myStmt.setString(1,theSupplier.getFirstName());
	myStmt.setString(2,theSupplier.getLastName());
	myStmt.setString(3,theSupplier.getPhone());
	myStmt.setString(4,theSupplier.getLocation());
	
	//execute sql insert
	myStmt.execute();
	}
	finally {
		// clean up JDBC objects	
		close(myConn, myStmt, null);	
	}
    }
	//method to get Suppliers from database
	public Suppliers getSuppliers(String theSupplierId)
	throws Exception{
Suppliers theSupplier = null;
Connection myConn = null;
PreparedStatement myStmt = null;
ResultSet myRs = null;
int supplierId;
try {
	//convert supplier id to int
	supplierId = Integer.parseInt(theSupplierId);
	//get connection to database
	myConn = dataSource.getConnection();
	//create sql to get selected supplier
	String sql = "select * from spl_suppliers where id=?";
	//create prepared statement
	myStmt = myConn.prepareStatement(sql);
	// set param
	myStmt.setInt(1, supplierId);
	//execute statement
	myRs = myStmt.executeQuery();
	//retrieve data from result set row
	if(myRs.next()) {
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String phone = myRs.getString("phone");
		String location = myRs.getString("location");
		
		// use the SupplierId during construction
		theSupplier = new Suppliers(supplierId,firstName,lastName,phone,location);
	}
	else {
		throw new Exception("Could not find Supplier id" + supplierId); 
	}
	
	return theSupplier;
}
finally {
	//clean up JDBC object
	close(myConn,myStmt,myRs);
}
	}
	
	//Method to update supplier
	public void updateSupplier(Suppliers theSupplier)throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
		// get db connection 
		myConn = dataSource.getConnection();
		// create SQL update statement
		String sql = "update spl_suppliers "
		            + " set first_name=?, last_name=?, phone=?, location=? "
				    + "where id=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		// set params
		myStmt.setString(1, theSupplier.getFirstName());
		myStmt.setString(2, theSupplier.getLastName());
		myStmt.setString(3, theSupplier.getPhone());
		myStmt.setString(4, theSupplier.getLocation());
		myStmt.setInt(5, theSupplier.getId());
		//execute SQL statement
		myStmt.execute();
		}
		finally {
			//clean up JDBC objects
			close(myConn, myStmt, null);
		}	
		
	}
	//method to delete suppliers
	public void deleteSupplier(String theSupplierId)
	throws Exception{
	Connection myConn = null;
	PreparedStatement myStmt=null;
	try {
	//convert supplier id to int
	int supplierId = Integer.parseInt(theSupplierId);
	//get connection to database
	myConn = dataSource.getConnection();
	//create sql to delete student
	String sql = "delete from spl_suppliers where id=?";
	//prepare statement
	myStmt = myConn.prepareStatement(sql);
	//set params
	myStmt.setInt(1,supplierId);
	//execute sql statement
	myStmt.execute();
		
	}
	finally {
		//clean up JDBC code
		close(myConn,myStmt,null);
	}
		
	}
	
}