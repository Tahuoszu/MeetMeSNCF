<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="../css/bootstrapValidator.min.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<title>Contact</title>
</head>
<body>

  <jsp:include page="navBar.jsp">
    <jsp:param name="active" value="contact" />
  </jsp:include>
     
  <div class="container">
    <form role="form" method="post" action="/contact"
      class="form-horizontal col-md-offset-3 col-md-6">

      <div class="form-group">
        <input id="name" name="name" type="text"
          class="form-control col-md-3" placeholder="Votre nom" />
      </div>

      <div class="form-group">
        <input id="email" name="email" type="email"
          class="form-control col-md-3"
          placeholder="Votre adresse e-mail" />
      </div>

      <div class="form-group">
        <textarea id="message" name="message"
          class="form-control col-md-6" placeholder="Votre message"
          rows="5"></textarea>
      </div>

      <div class="form-group">
        <button type="submit"
          class="btn btn-primary input-medium pull-right">Envoyer</button>
      </div>

    </form>
  </div>

</body>
</html>
