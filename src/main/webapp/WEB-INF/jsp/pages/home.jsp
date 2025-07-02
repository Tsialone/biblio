<%@ page import="s4.biblio.models.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="s4.biblio.views.V_Abonnement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur"); 
List<V_Abonnement> all_v_abonnement = (List<V_Abonnement>) request.getAttribute("all_v_abonnement");
boolean deja_abonne = (boolean) request.getAttribute("deja_abonne");
%>

<div class="container my-5">
  <div class="card shadow-sm">
    <div class="card-body">
      <h2 class="card-title mb-3">Bonjour, <%= utilisateur.getNom() %></h2>
      <h5 class="text-muted mb-4">Utilisateur: <%= utilisateur.getCategorie().getLibelle() %></h5>
      <p class="text-secondary">Bienvenue dans notre page de Biblio ko.</p>

      <% if (deja_abonne) { %>
        <div class="table-responsive mt-4">
          <table class="table table-bordered table-hover">
            <thead class="table-light">
              <tr>
                <th scope="col">Date de début</th>
                <th scope="col">Date de fin</th>
                <th scope="col">Etat</th>

              </tr>
            </thead>
            <tbody>
              <% for (V_Abonnement v_abonnement : all_v_abonnement) { %>
                <tr>
                  <td><%= v_abonnement.getDateDebut() %></td>
                  <td><%= v_abonnement.getDateFin() %></td>
                  <td><%= v_abonnement.getEtat() %></td>
                </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      <% } else { %>
        <div class="alert alert-warning mt-4" role="alert">
          Vous n'êtes pas encore abonné.
          <a href="/abonnement/form" class="btn btn-sm btn-primary ms-3">S'abonner maintenant</a>
        </div>
      <% } %>
    </div>
  </div>
</div>
