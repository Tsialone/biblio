<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="s4.biblio.models.Pret" %>
<%@ page import="s4.biblio.models.Exemplaire" %>
<%@ page import="s4.biblio.models.Utilisateur" %>
<%@ page import="s4.biblio.models.Categorie" %>

<%
    List<Pret> prets = (List<Pret>) request.getAttribute("prets");
%>

<div class="container mt-4">
    <h4 class="mb-3">Liste des prêts</h4>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
            <tr>
                <th>#</th>
                <th>Exemplaire</th>
                <th>Catégorie</th>
                <th>Date début</th>
                <th>Date fin prevu</th>
            </tr>
        </thead>
        <tbody>
            <% if (prets != null && !prets.isEmpty()) {
                int i = 1;
                for (Pret pret : prets) {
            %>
            <tr>
                <td><%= i++ %></td>
                <td><%= pret.getExemplaire().getLivre().getTitre() %></td>
                <td><%= pret.getCategorie().getLibelle() %></td>
                <td><%= pret.getDateDebut() %></td>
                <td><%= pret.getDateFin() %></td>
            </tr>
            <%   }
               } else { %>
            <tr>
                <td colspan="6" class="text-center">Aucun prêt trouvé.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>
