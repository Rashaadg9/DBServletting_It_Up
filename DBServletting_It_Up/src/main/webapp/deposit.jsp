<%@ include file="loggedInheader.jsp" %>

	<div class="container">

		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Make a deposit</h1>
			</div>
		</div>

		<!-- action: where is the data going, where are you sending the data
		     Send data to the LoginServlet (make sure name matches class) -->
		<% String formType = "deposit"; %>
	

		<form action="<%= request.getContextPath() %>/<%= formType %>" method="post" >
		
			<!-- If sending data to a servlet, make sure each input has a name field (used when sending request) -->
			<div class="form-group">
				<label for="deposit">Amount to deposit</label> 
				<input type="number" id="deposit" name = "deposit" min="1" step=".01" class="form-control" required>
			</div>
			
			<input type="submit" class="btn btn-primary"> 
		</form>


	</div>

<%@ include file="footer.jsp" %>