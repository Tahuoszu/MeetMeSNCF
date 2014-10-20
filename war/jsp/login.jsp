<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>
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

    <form method="post"
      action="/login" id="loginForm"
      class="form-horizontal well col-md-offset-4 col-md-4" role="form">

      <fieldset>
        <legend>Connexion</legend>

        <div
          class="form-group<c:if test="${errorLogin}"> has-error</c:if> col-md-12">
          <input type="text" name="login" placeholder="Identifiant"
            class="form-control" value="<c:out value="${oldLogin}"/>" />
        </div>

        <div
          class="form-group<c:if test="${errorLogin}"> has-error</c:if> col-md-12">
          <input type="password" name="password"
            placeholder="Mot de passe" class="form-control" />
          <c:if test="${errorLogin}">
            <span class="help-block">Le login ou le mot de passe
              saisi est incorrect</span>
          </c:if>
        </div>

        <div class="form-group col-md-12">
          <button class="btn btn-md btn-primary" type="submit">S'identifier</button>
          <div class="signInFooter">
            Pas encore inscrit(e) ? <a href="jsp/register.jsp">S'inscrire</a>
          </div>
        </div>

      </fieldset>

    </form>

  </div>
</body>
</html>