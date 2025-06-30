<section class="px-6 py-6">
  <h2 class="text-2xl font-semibold text-gray-800 mb-6">Rapport de performance</h2>

  <!-- Cartes de statistiques -->
  <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
    
    <!-- Temps moyen de résolution -->
    <div class="bg-white rounded-xl shadow p-5">
      <p class="text-sm text-gray-500">Temps moyen de résolution</p>
      <p class="text-2xl font-bold text-blue-600 mt-1">4h 25min</p>
    </div>

    <!-- Satisfaction moyenne par agent -->
    <div class="bg-white rounded-xl shadow p-5">
      <p class="text-sm text-gray-500">Satisfaction moyenne (agents)</p>
      <p class="text-2xl font-bold text-green-600 mt-1">4.6 / 5</p>
    </div>

    <!-- Tickets ouverts / fermés -->
    <div class="bg-white rounded-xl shadow p-5">
      <p class="text-sm text-gray-500">Tickets cette semaine</p>
      <div class="flex items-center justify-between mt-2">
        <div class="text-sm text-gray-700">Ouverts : <span class="font-bold text-orange-500">18</span></div>
        <div class="text-sm text-gray-700">Fermés : <span class="font-bold text-green-500">24</span></div>
      </div>
    </div>

  </div>

  <!-- Graphique (maquette statique) -->
  <div class="bg-white p-6 rounded-xl shadow">
    <h3 class="text-lg font-semibold text-gray-800 mb-4">Évolution hebdomadaire des tickets</h3>
    <div class="h-48 bg-gray-100 flex items-center justify-center text-gray-400 italic">
      📊 Graphique à intégrer ici (ex: Chart.js, ApexCharts, Recharts...)
    </div>
  </div>
</section>
