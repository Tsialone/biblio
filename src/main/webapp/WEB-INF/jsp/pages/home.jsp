<%@ page import="s4.biblio.models.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="s4.biblio.views.V_Abonnement" %>

<%
Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); 
List<V_Abonnement> all_v_abonnement = (List<V_Abonnement>)request.getAttribute("all_v_abonnement");
boolean  deja_abonne = (boolean)request.getAttribute("deja_abonne");
%>
<div class="bg-white p-6 rounded shadow">
  <h2 class="text-xl font-semibold mb-4">Bonjour <%= utilisateur.getNom() %></h2>
  <h4 class="text-xl font-semibold mb-4">Type: <%= utilisateur.getCategorie().getLibelle() %></h2>
  <p class="text-gray-600">Bienvenue dans notre page de Biblio</p>


  <% if  (deja_abonne) { %>
    <table border =1 >
        <tr>
          <th>debut</th>
          <th>fin</th>
        </tr>
        <% for (V_Abonnement v_abonnement :all_v_abonnement) { %>
            <tr>
              <td><%= v_abonnement.getDateDebut()%></td>
              <td><%= v_abonnement.getDateFin()%></td>
            </tr>
        <% }  %>
      </table>
  <% }  else { %>
      <p>Vous n'est pas encore abonee</p>
  <% }  %>
  <div class="overflow-x-auto">
</div>
</div>
