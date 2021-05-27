<%--
  Created by IntelliJ IDEA.
  User: debby
  Date: 20/05/2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.domain.Contact" %>

<%
    Contact book = (Contact) request.getAttribute("book");
%>

<html>
<body>
<p>Thank you <%=book.getFullname()%> for contacting us from <%=book.getEmail()%>!!!!</p>
<a href="index.jsp">Home</a>
</body>
</html>
