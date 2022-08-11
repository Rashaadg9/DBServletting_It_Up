
<%@ include file="loggedInheader.jsp" %>

<div class="container">
	
	<h1>Dollars Bank Welcomes You!</h1>
	<h1>Welcome ${ firstname } | Your balance is $${ cash }</h1>
	<br></br><a href="<%= request.getContextPath() %>/transactions">View Transaction</a>
	<br></br><a href="<%= request.getContextPath() %>/deposit.jsp">Make a Deposit</a>
	<br></br><a href="<%= request.getContextPath() %>/toWithdrawal">Make a Withdrawal</a>
	<br></br><a href="<%= request.getContextPath() %>/accInfo">View Account Info</a>
	
	
</div>

<%@ include file="footer.jsp" %>
