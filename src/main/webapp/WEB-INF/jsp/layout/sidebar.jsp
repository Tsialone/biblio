<%@ page import="s4.biblio.models.Categorie" %>
<%@ page import="s4.biblio.models.Utilisateur" %>
<%@ page import="s4.biblio.models.E_TypeCategorie" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); 
%>

<aside class="bg-white shadow-sm d-none d-md-block" style="width: 16rem;">
  <div class="p-4 border-bottom fw-bold fs-5">
    <a href="/utilisateur/home" class="text-decoration-none text-dark">Biblio ko</a>
  </div>

  <nav class="p-3">
    <div class="mb-4">
      <% if (utilisateur.getCategorie().getType() == E_TypeCategorie.admin) { %>
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">Pret(s)</h3>
      <a href="/pret/form" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Ajout</a>
      <a href="/reservation/admin/list" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Reservation(s)</a>
      <a href="/prolongement/admin/list" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Prolongement(s)</a>

    </div>
    <div class="mb-4">
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">Stat(s)</h3>
      <a href="/dashboard/stats" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Dashboard</a>
    </div>
      <%-- <a href="/user/client/filter" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Prolonge</a> --%>
     <% } else { %>
     <div class="mb-4">
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">Pret(s)</h3>
      <a href="/pret/list" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Mes prets</a>
      <a href="/pret/remise/form" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Remise</a>

     </div>
     <div class="mb-4">
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">Reservation(s)</h3>
      <a href="/reservation/form" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Demande</a>
      <a href="/reservation/list" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Mes reservations</a>
     </div>
      <div class="mb-4">
        <h3 class="text-muted text-uppercase small fw-semibold mb-2">Abonnement</h3>
        <a href="/abonnement/form" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Renouveler</a>
      </div>
     <% } %>
      
    </div>
  </nav>
</aside>

<style>
  .hover-bg-light:hover {
    background-color: #f8f9fa; /* équivalent bg-gray-200 en Tailwind */
  }
</style>
