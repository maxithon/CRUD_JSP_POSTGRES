<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head><title>Suppliers info</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

<!--  get the suppliers from the request object (sent by servlet) -->

</body>
<br/>
<div class="container">
<!-- put new button: Add Student -->
<input type="button" value="Add Student" 
       onclick="window.location.href='add-suppliers-form.jsp';return false;"
       class="btn btn-success">
<br/>  <br/>     
<div class="row">
<div class="table-responsive">
<table class="table table-striped table-bordered table-hover " >
<tr class="table-success">
<th>FirstName</th>
<th>LastName</th>
<th>Phone</th>
<th>Location</th>
<th>Action</th>
</tr>
<c:forEach var="tempSupplier" items="${SUPPLIERS_LIST}">
<!-- set up a link for add each Supplier -->
<c:url var="tempLink" value="SuppliersControllerServlet">
<c:param name="command" value="LOAD" />
<c:param name="supplierId" value="${tempSupplier.id}" />
</c:url>
<!-- set up a link to delete a supplier-->
<c:url var="deletelink" value="SuppliersControllerServlet">
<c:param name="command" value="DELETE" />
<c:param name="supplierId" value="${tempSupplier.id}" />
</c:url>

<tr>
<td> ${tempSupplier.firstName} </td>
<td> ${tempSupplier.lastName} </td>
<td> ${tempSupplier.phone} </td>
<td> ${tempSupplier.location} </td>
<td><a href="${tempLink}">Update</a>
|
<a href="${deletelink}"
 onClick="if (!(confirm('Are you sure you want to delete this data'))) return false"
 >
 Delete
 </a>
 
 </td>
</tr>
</c:forEach>

</table>
</div>
</div>
</div>
</body>

</html>