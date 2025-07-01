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
    <h2 class="text-center fw-bold mb-4 text-secondary fs-4">Inscription</h2>

    <form action="/utilisateur/verify" method="POST" class="needs-validation" novalidate>
      

      <div class="mb-3">
        <label for="email" class="form-label fw-medium">Nom</label>
        <input
          type="nom"
          id="nom"
          name="nom"
          class="form-control"
          placeholder="Ex: Bobou"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre nom.
        </div>  


        <div class="mb-3">
        <label for="email" class="form-label fw-medium">Prenom</label>
        <input
          type="email"
          id="email"
          name="email"
          class="form-control"
          placeholder="Ex: Babakoto"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre prenom.
        </div>

      <div class="mb-3">
        <label for="email" class="form-label fw-medium">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          class="form-control"
          placeholder="Ex: tsialone1902@gmail.com"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre email.
        </div>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label fw-large">Tel</label>
        <input
          type="tel"
          id="tel"
          name="tel"
          class="form-control"
          placeholder="Ex: 0387588451"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre email.
        </div>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label fw-large">Addresse</label>
        <input
          type="tel"
          id="tel"
          name="tel"
          class="form-control"
          placeholder="Ex: Analamanga"
          required
        />
        <div class="invalid-feedback">
          Votre addresse.
        </div>
      </div>
      <div class="mb-4">
        <label for="password" class="form-label fw-medium">Mot de passe</label>
        <input
          type="password"
          id="password"
          name="password"
          class="form-control"
          placeholder="Ex: 239higon3riq3j9h3i2qofji3"
          required
        />
        <div class="invalid-feedback">
          Veuillez entrer votre mot de passe.
        </div>
      </div>
      
      <div class="text-center mt-3">
        <span>Deja inscrit ?</span>
        <a href="/utilisateur/login" class="text-primary fw-semibold">Login</a>
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
