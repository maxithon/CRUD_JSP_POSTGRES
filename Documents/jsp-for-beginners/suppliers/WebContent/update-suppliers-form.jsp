<!DOCTYPE html>
<html>
<head><title>Update Supplier</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<div class="container">
<br/>
<div class="row">
<div class="col-8">
<div class="mb-2 bg-secondary text-white rounded text-center border border-dark"><h3>Record Suppliers Information</h3></div>

<form action="SuppliersControllerServlet" method="GET">
<input type="hidden" name="command" value="UPDATE" />
<input type="hidden" name="supplierId" value="${THE_SUPPLIERS.id}" />

FirstName:
<input type="text" name="firstName" class="form-control" 
       value="${THE_SUPPLIERS.firstName}" />

LastName:

<input type="text" name="lastName" class="form-control" 
        value="${THE_SUPPLIERS.lastName}" />

Phone:

<input type="text" name="phone" class="form-control" 
       value="${THE_SUPPLIERS.phone}" />

Location:

<input type="text" name="location" class="form-control"
        value="${THE_SUPPLIERS.location}" />

<br/>
<input type="submit" value="Save" class="btn btn-primary">

</form>
</div>
</div>
<br/>
<a href="SuppliersControllerServlet">Back to List</a>
</div>
</body>
</html>