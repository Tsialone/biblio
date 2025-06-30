<section class="px-6 py-6 bg-gray-50 rounded-lg mb-6 shadow-sm">
  <form method="get" action="tickets.jsp" class="grid grid-cols-1 md:grid-cols-4 gap-4 items-end">


    <!-- Filtre par client -->
    <div>
      <label for="client" class="block text-sm font-medium text-gray-700 mb-1">Client</label>
      <select id="client" name="client"
              class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        <option value="">Tous</option>
        <option value="jean">Jean Dupont</option>
        <option value="sophie">Sophie Rasoanaivo</option>
        <!-- Ajouter d'autres clients dynamiquement -->
      </select>
    </div>

    <!-- Filtre par statut -->
    <div>
      <label for="statut" class="block text-sm font-medium text-gray-700 mb-1">Statut</label>
      <select id="statut" name="statut"
              class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        <option value="">Tous</option>
        <option value="nouveau">Nouveau</option>
        <option value="encours">En cours</option>
        <option value="resolu">Résolu</option>
      </select>
    </div>

    <!-- Filtre par priorité -->
    <div>
      <label for="priorite" class="block text-sm font-medium text-gray-700 mb-1">Priorité</label>
      <select id="priorite" name="priorite"
              class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
        <option value="">Toutes</option>
        <option value="basse">Basse</option>
        <option value="moyenne">Moyenne</option>
        <option value="elevee">Élevée</option>
      </select>
    </div>

    <!-- Bouton filtrer -->
    <div class="md:col-span-4 text-right">
      <button type="submit"
              class="inline-block bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700 transition">
        Filtrer
      </button>
    </div>

  </form>
</section>
