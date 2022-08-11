
<%@ include file="loggedInheader.jsp" %>

<div class="container">
	
	<h1>User List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Username</th>
				<th>Password</th>
				<th>Pin</th>
				<th>Cash</th>
			</tr>
		</thead>
		
		<tbody>
				
				<tr>
					
					<td>
						<c:out value="${ first_name }" />
					</td>
					
					<td>
						<c:out value="${ last_name }" />
					</td>
					
					<td>
						<c:out value="${ username }" />
					</td>
					
					<td>
						<c:out value="${ pass }" />
					</td>
					
					<td>
						<c:out value="${ pin }" />
					</td>
					
					<td>
						<c:out value="${ cash }" />
					</td>
					
				</tr>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>