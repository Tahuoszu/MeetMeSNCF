<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>S'enregistrer</title>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
  href="../css/bootstrapValidator.min.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../js/javascript.js"></script>
</head>
<body>

  <div class="container">
    <form role="form" method="post"
      action="/register"
      id="registerForm"
      class="form-horizontal well col-md-offset-3 col-md-6">

      <fieldset>
        <legend>Inscription</legend>

        <c:set var="classColG" value="col-md-4" />
        <c:set var="classColD" value="col-md-8" />

        <div class="form-group row">
          <label class="${classColG} control-label">Pseudo <span
            class="requis">*</span></label>
          <div class="${classColD}">
            <input type="text" name="login" class="form-control"
              value="<c:out value="${registerForm.login}"/>" />
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Mot de passe
            <span class="requis">*</span>
          </label>
          <div class="${classColD}">
            <input type="password" name="password" class="form-control"
              value="<c:out value="${registerForm.password}"/>" />
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Confirmation
            <span class="requis">*</span>
          </label>
          <div class="${classColD}">
            <input type="password" name="confirmation"
              class="form-control"
              value="<c:out value="${registerForm.confirmation}"/>" />
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">E-mail <span
            class="requis">*</span></label>
          <div class="${classColD}">
            <input type="text" name="email" class="form-control"
              value="<c:out value="${registerForm.email}"/>" />
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Prénom</label>
          <div class="${classColD}">
            <input type="text" name="firstname" class="form-control"
              value="<c:out value="${registerForm.firstname}"/>" />
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Nom</label>
          <div class="${classColD}">
            <input type="text" name="lastname" class="form-control"
              value="<c:out value="${registerForm.lastname}"/>" />
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Sexe <span
            class="requis">*</span></label>
          <div class="${classColD} selectContainer">
            <select name="sexe" class="form-control">
              <option value=""></option>
              <option value="male">Masculin</option>
              <option value="female">Féminin</option>
            </select>
          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Centres
            d'intérêt</label>
          <div class="${classColD}">

            <div class="checkbox">
              <label> <input type="checkbox" name="interests" value="Musique" />
                Musique
              </label>
            </div>

            <div class="checkbox">
              <label> <input type="checkbox" name="interests" value="Sport" />
                Sport
              </label>
            </div>

            <div class="checkbox">
              <label> <input type="checkbox" name="interests" value="Cinéma" />
                Cinéma
              </label>
            </div>

            <div class="checkbox">
              <label> <input type="checkbox" name="interests" value="Jeux vidéos" />
                Jeux vidéos
              </label>
            </div>

          </div>
        </div>

        <div class="form-group row">
          <label class="${classColG} control-label">Présentation
            <span class="requis">*</span>
          </label>
          <div class="${classColD}">
            <textarea name="presentation" class="form-control"><c:out
                value="${registerForm.presentation}" /></textarea>
          </div>
        </div>

        <button type="submit" class="pull-right btn btn-primary">S'inscrire</button>

      </fieldset>
    </form>
  </div>
</body>
</html>