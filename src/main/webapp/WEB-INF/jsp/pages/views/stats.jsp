<%@ page import="s4.biblio.models.Utilisateur" %>
<%@ page import="s4.biblio.views.VExemplaireEmprunt" %>
<%@ page import="s4.biblio.models.StatPenaliteAdherantView" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    List<VExemplaireEmprunt> exemplaires = (List<VExemplaireEmprunt>) request.getAttribute("exemplaires");
    List<StatPenaliteAdherantView> stat_user = (List<StatPenaliteAdherantView>) request.getAttribute("stat_user");

    int  nbr_adherant_penalise = (int)request.getAttribute("nbr_adherant_penalise");
    double  duree_moyenne_pret = (double)request.getAttribute("duree_moyenne_pret");
    double  duree_moyenne_penalite = (double)request.getAttribute("duree_moyenne_penalite");


%>

<div class="container mt-4">
    <h4 class="mb-4">Statistiques</h4>

    <div class="row mb-4">
        <div class="col-md-4">
            <div class="card text-white bg-danger mb-3 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Nombre Adhérents pénalisés</h5>
                    <p class="card-text" style="font-size: 1.5rem;"><strong><%= nbr_adherant_penalise %></strong></p>
                </div>
            </div>
        </div>
          <div class="col-md-4">
            <div class="card text-white bg-info mb-3 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Duree moyenne des prets</h5>
                    <p class="card-text" style="font-size: 1.5rem;"><strong><%= duree_moyenne_pret %></strong></p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-white bg-success mb-3 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Duree moyenne des penalites</h5>
                    <p class="card-text" style="font-size: 1.5rem;"><strong><%= duree_moyenne_penalite %></strong></p>
                </div>
            </div>
        </div>
    </div>
    
    <% if (stat_user != null && !stat_user.isEmpty()) { %>
        <table class="table table-sm table-bordered table-striped">
            <thead class="thead-light">
                <tr>
                    <th>ID adherant</th>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th>total penalite</th>
                </tr>
            </thead>
            <tbody>
                <% for (StatPenaliteAdherantView ex : stat_user) { %>
                    <tr>
                        <td><%= ex.getAdherantId() %></td>
                        <td><%= ex.getNomAdherant() %></td>
                        <td><%= ex.getPrenomAdherant() %></td>
                        <td><%= ex.getTotalPenalites() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <div class="alert alert-info">
            Aucune donnée trouvée.
        </div>
    <% } %>

    <% if (exemplaires != null && !exemplaires.isEmpty()) { %>
        <table class="table table-sm table-bordered table-striped">
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
