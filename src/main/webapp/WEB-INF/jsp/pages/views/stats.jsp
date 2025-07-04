<%@ page import="s4.biblio.models.Utilisateur" %>
<%@ page import="s4.biblio.views.VExemplaireEmprunt" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    List<VExemplaireEmprunt> exemplaires = (List<VExemplaireEmprunt>) request.getAttribute("exemplaires");
%>

<div class="container mt-4">
    <h4 class="mb-4">Statistiques des emprunts par exemplaire</h4>

    <% if (exemplaires != null && !exemplaires.isEmpty()) { %>
        <table class="table table-bordered table-striped">
            <thead class="thead-light">
                <tr>
                    <th>ID Exemplaire</th>
                    <th>Nombre d'emprunts</th>
                </tr>
            </thead>
            <tbody>
                <% for (VExemplaireEmprunt ex : exemplaires) { %>
                    <tr>
                        <td><%= ex.getId_exemplaire() %></td>
                        <td><%= ex.getNbr_emprunt() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <div class="alert alert-info">
            Aucune donnée trouvée.
        </div>
    <% } %>
</div>
