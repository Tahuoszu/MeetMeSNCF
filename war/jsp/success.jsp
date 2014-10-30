<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Success</title>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="../css/bootstrapValidator.min.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/javascript.js"></script>
</head>
<body>

    <jsp:include page="navBar.jsp"></jsp:include>

	<form class="form-horizontal well col-md-offset-3 col-md-6" role="form">
		<fieldset>
			<legend><h3>Inscription Réussie !</h3></legend>
			<div class="container">
				<h4>Bienvenu(e) sur MeetMeSNCF !</h4><br />
				Un mail de confirmation vous a été envoyé !<br /><br />
				Félicitation ! Vous faites maintenant partie de la
				communauté des gens<br /> qui se sentiront moins seuls dans les trains
				délabrés de la SNCF !<br /><br />
				<p>Voyagez le coeur léger et trouvez-vous des amis (ou pas) !</p>
			</div>
		</fieldset>
	</form>
</body>
</html>