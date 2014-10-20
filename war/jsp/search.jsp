<%@ page language="java" 
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Recherche</title>
    <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="../css/bootstrapValidator.min.css" />
    <link type="text/css" rel="stylesheet" href="../css/style.css" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="../js/javascript.js"></script>  
  </head>
  <body>
  
  	<div class="container">
      <form class="form-horizontal well col-md-offset-4 col-md-5"
      method="post" action="/search" id="searchForm">
		<fieldset>
			<!-- Form Name -->
			<legend>Rechercher un train</legend>
			<!-- Select Basic -->
			<div class="control-group">
			  <label class="control-label" for="depart">Départ :</label>
			  <div class="controls">
			    <select id="depart" name="depart" class="input-large">
			      <option>Gare1</option>
			      <option>Gare2</option>
			    </select>
			  </div>
			</div>
			
			<!-- Select Basic -->
			<div class="control-group">
			  <label class="control-label" for="arrivee">Arrivée :</label>
			  <div class="controls">
			    <select id="arrivee" name="arrivee" class="input-large">
			      <option>Gare1</option>
			      <option>Gare2</option>
			    </select>
			  </div>
			</div>
			
			<!-- Button -->
			<div class="control-group">
			  <label class="control-label" for="recherche"></label>
			  <div class="controls">
			    <button id="recherche" name="recherche" class="btn btn-primary">Rechercher</button>
			  </div>
			</div>
		</fieldset>
    	<br/>
    	
        <fieldset>
          <legend>Rechercher un membre</legend>
          <div class="form-group col-md-12">
            <input type="text" name="user" placeholder="Membre" class="form-control" />
          </div>
          <div class="control-group">
			  <label class="control-label" for="recherche"></label>
			  <div class="controls">
			    <button id="recherche" name="recherche" class="btn btn-primary">Rechercher</button>
			  </div>
		  </div>
        </fieldset>              
      </form>
    </div>
    
    <div class="container">
	    <div class="col-md-offset-2 col-md-8">
	      <table class="table table-striped table-hover">
	        <thead>
	          <tr>
	            <th>Nom</th>
	            <th>Code</th>
	            <th>Destination</th>
	            <th>Etat</th>
	            <th>Horaire</th>
	          </tr>
	        </thead>
	        <tbody>
	        </tbody>
	      </table>
	    </div>
	  </div>
	  <br/>
    
      <%@ page import="domain.User,java.util.List,java.util.ArrayList"%>
  <%
      // ---------- Pour tester
      List<User> usersList = new ArrayList<User>();
      User u = new User();
      u.setLogin("Bob l'éponge"); u.setSexe("male");
      u.setPresentation("Je suis une éponge !");
      List<String> interests = new ArrayList<String>(); interests.add("Musique");interests.add("Cinéma");
      u.setInterests(interests);
      usersList.add(u);
      User u2 = new User();
      u2.setLogin("Dora l'exploratrice"); u2.setSexe("female"); u2.setFirstname("Dora");
      u2.setLastname("Diego");
      u2.setPresentation("Je t'apprends la langue !");
      usersList.add(u2);
      User u3 = new User();
      u3.setLogin("Tintin"); u3.setSexe("male");
      u3.setPresentation("Je suis un détective !");
      usersList.add(u3);
      request.setAttribute("usersList", usersList);
      // ---------- Pour tester
  %>
  <div class="container">
    <div class="col-md-offset-2 col-md-8">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>Pseudo</th>
            <th>Sexe</th>
            <th>Présentation</th>
            <th>Centres d'intérêt</th>
            <th>Chat</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="user" items="${usersList}">
            <tr>
              <td class="login"><c:out value="${user.login}" /></td>
              <td>
                <c:if test="${user.sexe eq 'male'}">Masculin</c:if>
                <c:if test="${user.sexe eq 'female'}">Féminin</c:if>
              </td>
              <td><c:out value="${user.presentation}" /></td>
              <td>
                <ul>
                  <c:forEach var="interest" items="${user.interests}">
                    <li><c:out value="${interest}" /></li>
                  </c:forEach>
                </ul>
              </td>
              <td>
                <button type="button" class="btn btn-primary btn-xs">Envoyer un message</button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    
  </body>
</html>