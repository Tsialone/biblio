<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String title = request.getAttribute("title").toString();
  String the_page = request.getAttribute("content").toString();
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title><%= title %></title>
  <link rel="icon" type="image/png" href="../assets/images/my.png">
  
  <!-- Bootstrap CSS -->
  <%-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"> --%>
  <link href="../../assets/bootstrap-5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body class="bg-light d-flex min-vh-100">

  <!-- Sidebar -->
  <jsp:include page="layout/sidebar.jsp" />

  <!-- Main Content Area -->
  <div class="flex-grow-1 d-flex flex-column">

    <!-- Header -->
    <jsp:include page="layout/header.jsp" />

    <!-- Main -->
    <main class="p-4">
      <jsp:include page="<%= the_page %>" />
    </main>

  </div>

  <!-- Bootstrap JS (optional, for dropdowns, collapse, etc.) -->
  <%-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> --%>
  <script src="../../assets/bootstrap-5.0.2/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
