<aside class="bg-white shadow-sm d-none d-md-block" style="width: 16rem;">
  <div class="p-4 border-bottom fw-bold fs-5">Gestion Ticket</div>

  <nav class="p-3">
    <div class="mb-4">
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">View</h3>
      <a href="/tickets/all" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Tickets</a>
      <a href="/user/client/all" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Clients</a>
      <a href="/user/client/filter" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Dashboard-Client</a>
    </div>
    <div class="mb-4">
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">Stat(s)</h3>
      <a href="/tickets/performances" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Performance</a>
    </div>
    <div>
      <h3 class="text-muted text-uppercase small fw-semibold mb-2">Message</h3>
      <a href="/tickets/tickets_report" class="d-block p-2 rounded text-decoration-none text-body hover-bg-light">Report</a>
    </div>
  </nav>
</aside>

<style>
  .hover-bg-light:hover {
    background-color: #f8f9fa; /* équivalent bg-gray-200 en Tailwind */
  }
</style>
