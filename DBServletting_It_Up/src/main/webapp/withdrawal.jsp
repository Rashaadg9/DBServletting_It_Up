<%@ include file="loggedInheader.jsp" %>

	<div class="container">

		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1 class="display-4">Make a Withdrawal $${ cash }</h1>
			</div>
		</div>

		<!-- action: where is the data going, where are you sending the data
		     Send data to the LoginServlet (make sure name matches class) -->
		<% String formType = "withdrawal"; %>
	

		<form action="<%= request.getContextPath() %>/<%= formType %>" method="post" >
		
			<!-- If sending data to a servlet, make sure each input has a name field (used when sending request) -->
			<div class="form-group">
				<label for="withdrawal">Amount to withdraw</label> 
				<input type="number" id="withdrawal" name = "withdrawal" min="1" max ="${ cash }" step=".01" class="form-control" required>
			</div>
			
			<input type="submit" class="btn btn-primary"> 
		</form>


	</div>

<%@ include file="footer.jsp" %>