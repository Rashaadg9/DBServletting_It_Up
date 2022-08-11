
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "login"; %>
	

	<form action="<%= request.getContextPath() %>/<%= formType %>" method="post" >
		<h2>User Login</h2>
		<div class="form-group">
	
			<label for="username">Username</label>
	    	<input type="text" class="form-control" id="username" name="username" required>
	    	
		</div>
	
		<div class="form-group">
		
			<label for="password">Password</label>
		    <input type="password" class="form-control" id="password" name="password" required>
		    
		</div>
		
		<br></br><a href="<%= request.getContextPath() %>/new-form.jsp">Create a new account</a><br></br>
		
		<button type="submit" class="btn btn-primary" style="margin:10px">Submit</button>
	
	</form>
	
</div>


<%@ include file="footer.jsp" %>