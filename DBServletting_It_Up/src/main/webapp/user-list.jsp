
<%@ include file="header.jsp" %>

<div class="container">
	
	<h1>User List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Username</th>
				<th>Password</th>
				<th>Pin</th>
				<th>Cash</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="user" items="${ allUsers }">
				
				<tr>
					<td>
						<c:out value="${ user.user_id }" />
					</td>
					
					<td>
						<c:out value="${ user.first_name }" />
					</td>
					
					<td>
						<c:out value="${ user.last_name }" />
					</td>
					
					<td>
						<c:out value="${ user.username }" />
					</td>
					
					<td>
						<c:out value="${ user.pass }" />
					</td>
					
					<td>
						<c:out value="${ user.pin }" />
					</td>
					
					<td>
						<c:out value="${ user.cash }" />
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>