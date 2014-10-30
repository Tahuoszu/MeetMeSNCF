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

  <jsp:include page="navBar.jsp">
    <jsp:param name="active" value="register" />
  </jsp:include>

  <div class="container">
    <form role="form" method="post"
      action="/register"
      id="registerForm"
      class="form-horizontal well col-md-offset-3 col-md-6">

      <fieldset>
        <legend>Inscription</legend>

        <c:set var="classColG" value="col-md-4" />
        <c:set var="classColD" value="col-md-8" />

        <div class="form-group row<c:if test="${not empty errorsRegister['login']}"> has-error</c:if>">
          <label class="${classColG} control-label">Pseudo <span
            class="requis">*</span></label>
          <div class="${classColD}">
            <input type="text" name="login" class="form-control"
              value="<c:out value="${registerForm.login}"/>" />
            <c:if test="${not empty errorsRegister['login']}">
              <span class="help-block"><c:out value="${errorsRegister['login']}"/></span>
            </c:if>
          </div>
        </div>

        <div class="form-group row<c:if test="${not empty errorsRegister['password']}"> has-error</c:if>">
          <label class="${classColG} control-label">Mot de passe
            <span class="requis">*</span>
          </label>
          <div class="${classColD}">
            <input type="password" name="password" class="form-control"
              value="<c:out value="${registerForm.password}"/>" />
            <c:if test="${not empty errorsRegister['password']}">
              <span class="help-block"><c:out value="${errorsRegister['password']}"/></span>
            </c:if>
          </div>
        </div>

        <div class="form-group row<c:if test="${not empty errorsRegister['confirmation']}"> has-error</c:if>">
          <label class="${classColG} control-label">Confirmation
            <span class="requis">*</span>
          </label>
          <div class="${classColD}">
            <input type="password" name="confirmation"
              class="form-control"
              value="<c:out value="${registerForm.confirmation}"/>" />
            <c:if test="${not empty errorsRegister['confirmation']}">
              <span class="help-block"><c:out value="${errorsRegister['confirmation']}"/></span>
            </c:if>
          </div>
        </div>

        <div class="form-group row<c:if test="${not empty errorsRegister['email']}"> has-error</c:if>">
          <label class="${classColG} control-label">E-mail <span
            class="requis">*</span></label>
          <div class="${classColD}">
            <input type="text" name="email" class="form-control"
              value="<c:out value="${registerForm.email}"/>" />
            <c:if test="${not empty errorsRegister['email']}">
              <span class="help-block"><c:out value="${errorsRegister['email']}"/></span>
            </c:if>
          </div>
        </div>

        <div class="form-group row<c:if test="${not empty errorsRegister['firstname']}"> has-error</c:if>">
          <label class="${classColG} control-label">Prénom</label>
          <div class="${classColD}">
            <input type="text" name="firstname" class="form-control"
              value="<c:out value="${registerForm.firstname}"/>" />
            <c:if test="${not empty errorsRegister['firstname']}">
              <span class="help-block"><c:out value="${errorsRegister['firstname']}"/></span>
            </c:if>
          </div>
        </div>

        <div class="form-group row<c:if test="${not empty errorsRegister['lastname']}"> has-error</c:if>">
          <label class="${classColG} control-label">Nom</label>
          <div class="${classColD}">
            <input type="text" name="lastname" class="form-control"
              value="<c:out value="${registerForm.lastname}"/>" />
            <c:if test="${not empty errorsRegister['lastname']}">
              <span class="help-block"><c:out value="${errorsRegister['lastname']}"/></span>
            </c:if>
          </div>
        </div>

        <div class="form-group row<c:if test="${not empty errorsRegister['sexe']}"> has-error</c:if>">
          <label class="${classColG} control-label">Sexe <span
            class="requis">*</span></label>
          <div class="${classColD} selectContainer">
            <select name="sexe" class="form-control">
              <option value=""></option>
              <option value="male">Masculin</option>
              <option value="female">Féminin</option>
            </select>
            <c:if test="${not empty errorsRegister['sexe']}">
              <span class="help-block"><c:out value="${errorsRegister['sexe']}"/></span>
            </c:if>
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

        <div class="form-group row<c:if test="${not empty errorsRegister['presentation']}"> has-error</c:if>">
          <label class="${classColG} control-label">Présentation
            <span class="requis">*</span>
          </label>
          <div class="${classColD}">
            <textarea name="presentation" class="form-control"><c:out
                value="${registerForm.presentation}" /></textarea>
            <c:if test="${not empty errorsRegister['presentation']}">
              <span class="help-block"><c:out value="${errorsRegister['presentation']}"/></span>
            </c:if>
          </div>
        </div>

        <button type="submit" class="pull-right btn btn-primary">S'inscrire</button>

      </fieldset>
    </form>
  </div>
</body>
</html>