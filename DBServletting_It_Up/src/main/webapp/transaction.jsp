
<%@ include file="loggedInheader.jsp" %>

<div class="container">
	
	<h1>User List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>Date</th>
				<th>Type</th>
				<th>Amount</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="transaction" items="${ allTransactions }">
				
				<tr>
					<td>
						<c:out value="${ transaction.trans_date }" />
					</td>
					
					<td>
						<c:out value="${ transaction.trans_type }" />
					</td>
					
					<td>
						<c:out value="${ transaction.trans_amount }" />
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>