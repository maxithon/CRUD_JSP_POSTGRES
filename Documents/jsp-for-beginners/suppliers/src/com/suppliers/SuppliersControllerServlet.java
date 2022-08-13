package com.suppliers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class SuppliersControllerServlet
 */
@WebServlet("/SuppliersControllerServlet")
public class SuppliersControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private SuppliersDbUtil suppliersDbUtil;
@Resource(name="jdbc/suppliers_info")
private DataSource dataSource;
	@Override
public void init() throws ServletException {
	super.init();	
// create our student db util .. and pass in the conn pool / datasource
try {
	suppliersDbUtil = new SuppliersDbUtil(dataSource);
}
catch(Exception exc) {
	throw new ServletException(exc);
}
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {	
	//read the command from form 'add-suppliers-form.jsp
	String theCommand = request.getParameter("command");
	//if the command is missing, then default to listing students
	if(theCommand == null) {
		theCommand = "LIST";	
	}
	//route to the appropriate method
	switch(theCommand) {
	case "LIST":
	listSuppliers(request, response);	
	break;
	case "ADD":
	addSupplier(request, response);
	break;
	case "LOAD":
	loadSupplier(request, response);
	break;
	case "UPDATE":
	updateSupplier(request, response);
	break;
	
	case "DELETE":
	deleteSupplier(request,response);
	break;
	
	default:
	listSuppliers(request,response);
	}
	}
	catch (Exception exc) {
		throw new ServletException (exc);
	}
	}
	//Method to delete a Supplier
	private void deleteSupplier(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
    //read supplier id
    String theSupplierId = request.getParameter("supplierId");
    //delete supplier from database
    suppliersDbUtil.deleteSupplier(theSupplierId);
    //send them back to "list of suppliers
    listSuppliers(request,response);
	}
	//Method to update Supplier
	private void updateSupplier(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
	//read Supplier information
	int id= Integer.parseInt(request.getParameter("supplierId"));
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String phone = request.getParameter("phone");
	String location = request.getParameter("location");
	//create a new supplier object
	Suppliers theSupplier = new Suppliers(id,firstName,lastName,phone,location);
	//perform on database
	suppliersDbUtil.updateSupplier(theSupplier);
	//send them back to the "list suppliers page
	listSuppliers(request,response);
	}
	//Method for Loading Suppliers on Update form
	private void loadSupplier(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
	
	//read suppliers information
String theSupplierId = request.getParameter("supplierId");
	// get suppliers from database (db util)
Suppliers theSupplier = suppliersDbUtil.getSuppliers(theSupplierId);
	
//place Supplier in the request attribute
request.setAttribute("THE_SUPPLIERS",theSupplier);
// send to jsp page: update-supplier-form.jsp
RequestDispatcher dispatcher = 
       request.getRequestDispatcher("/update-suppliers-form.jsp");
dispatcher.forward(request, response);
	}
	
	// add suppliers method
	private void addSupplier(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
	//read suppliers information from form
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String phone = request.getParameter("phone");
	String location = request.getParameter("location");
	// create a new suppliers object
	Suppliers theSupplier = new Suppliers(firstName,lastName,phone,location);
	//add the Suppliers to the database
	suppliersDbUtil.addSupplier(theSupplier);
	//send back to main page 
	listSuppliers(request,response);
	}	
	private void listSuppliers(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		// get suppliers from db util
		List<Suppliers> supplier = suppliersDbUtil.getSuppliers();
		//add Suppliers to the request
		request.setAttribute("SUPPLIERS_LIST", supplier);
		//send to JSP page (view)
	RequestDispatcher dispatcher = request.getRequestDispatcher("/list_suppliers.jsp");
	dispatcher.forward(request, response);	
	}
}
