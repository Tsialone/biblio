<!-- Container tri aligné à droite -->
<!-- Formulaire de tri aligné à droite -->
<form class="mb-6 flex justify-end max-w-full px-4 space-x-3" action="#" method="get">
  <label for="sort-date" class="text-gray-700 font-medium whitespace-nowrap self-center">Trier par date :</label>
  <select id="sort-date" name="sort-date" 
          class="border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 cursor-pointer">
    <option value="desc" selected>Recent</option>
    <option value="asc">Ancien</option>
  </select>
  <button type="submit" 
          class="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition whitespace-nowrap">
    Valider
  </button>
</form>



<!-- Ta grille une colonne -->
<div class="grid grid-cols-1 gap-6 px-4">

  <div class="bg-white p-4 rounded-xl shadow hover:shadow-md transition relative">
    <time class="absolute top-4 right-4 text-xs text-gray-400">24 juin 2025</time>
    <h3 class="text-lg font-bold text-red-600">Connexion impossible</h3>
    <p class="text-gray-700 mt-2">L'utilisateur ne peut pas se connecter à la plateforme depuis ce matin.</p>
    <p class="text-sm text-gray-500 mt-2">Agent : <span class="font-medium">Jean Dupont</span></p>
    <a href="/user/discussion" class="text-blue-600 hover:underline text-sm mt-2 inline-block">Voir la discussion</a>
  </div>

  <div class="bg-white p-4 rounded-xl shadow hover:shadow-md transition relative">
    <time class="absolute top-4 right-4 text-xs text-gray-400">23 juin 2025</time>
    <h3 class="text-lg font-bold text-red-600">Erreur d'impression</h3>
    <p class="text-gray-700 mt-2">L'imprimante du bureau 3 génère une erreur lors de l'impression de fichiers PDF.</p>
    <p class="text-sm text-gray-500 mt-2">Agent : <span class="font-medium">Sophie Rasoanaivo</span></p>
    <a href="/user/discussion" class="text-blue-600 hover:underline text-sm mt-2 inline-block">Voir la discussion</a>
  </div>

</div>
