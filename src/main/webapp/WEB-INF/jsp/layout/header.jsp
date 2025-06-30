<%@page import="java.util.List"  %>
<%@page import="java.time.LocalDate"  %>
<% String fonctionality = request.getAttribute("fonctionality").toString(); %>

<header class="bg-white shadow-sm p-3 d-flex justify-content-between align-items-center">
  <h1 class="h4 fw-semibold"><%= fonctionality %></h1>
  <div class="d-flex align-items-center gap-3 ms-3">
    <span class="text-secondary">Bonjour, Admin</span>
    <a href="logout.jsp" class="btn btn-primary btn-sm">Log out</a>
  </div>
</header>
