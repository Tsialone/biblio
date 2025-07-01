<%@page import="java.util.List"  %>
<%@page import="java.time.LocalDate"  %>
<%@ page import="s4.biblio.models.Utilisateur" %>
<% String fonctionality = request.getAttribute("fonctionality").toString(); %>
<% Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
<header class="bg-white shadow-sm p-3 d-flex justify-content-between align-items-center">
  <h1 class="h4 fw-semibold"><%= fonctionality %></h1>
  <div class="d-flex align-items-center gap-3 ms-3">
    <span class="text-secondary">Bonjour, <%=utilisateur.getNom()%>|<%=utilisateur.getCategorie().getType()  %></span>
    <a href="/utilisateur/login" class="btn btn-primary btn-sm">Log out</a>
  </div>
</header>
