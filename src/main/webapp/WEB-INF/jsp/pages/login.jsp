<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Connexion</title>
  <link rel="icon" type="image/png" href="../../assets/images/my.png">
  <link href="../../assets/bootstrap-5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light d-flex align-items-center justify-content-center min-vh-100">

  <div class="bg-white p-4 p-md-5 rounded shadow-sm w-100" style="max-width: 400px;">
    <h2 class="text-center fw-bold mb-4 text-secondary fs-4">Connexion</h2>

    <form action="/utilisateur/verify" method="POST" class="needs-validation" novalidate>
      
      <div class="mb-3">
        <label for="email" class="form-label fw-medium">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          class="form-control"
          placeholder="Entrez votre email"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre email.
        </div>
      </div>
      <div class="mb-4">
        <label for="password" class="form-label fw-medium">Mot de passe</label>
        <input
          type="password"
          id="password"
          name="password"
          class="form-control"
          placeholder="Entrez votre mot de passe"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre mot de passe.
        </div>
      </div>
      
      <div class="text-center mt-3">
        <span>Pas encore inscrit ?</span>
        <a href="/user/register" class="text-primary fw-semibold">Inscription</a>
      </div>
      <br/>


      <button type="submit" class="btn btn-primary w-100 fw-semibold">
        Se connecter
      </button>
      <p class="text-danger text-center mt-3" id="error-message">
        Identifiants incorrects.
      </p>

    </form>
  </div>

  <%-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> --%>
  <script src="../../assets/bootstrap-5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
