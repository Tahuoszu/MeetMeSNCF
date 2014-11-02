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
<link type="text/css" rel="stylesheet" href="../css/jquery-ui.min.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />

</head>
<body onload="connectChat()">

  <jsp:include page="navBar.jsp">
    <jsp:param name="active" value="search" />
  </jsp:include>

  <h1 class="text-center">Bienvenue sur S.N.C.F. !</h1>
  <div class="container">

    <div class="row">
      <div class="col-md-9">

        <form method="post" action="/searchGare" id="searchForm"
          class="form-horizontal well col-md-offset-4 col-md-4">
          <fieldset>
            <!-- Form Name -->
            <legend>Rechercher un train</legend>
            <!-- Select Basic -->
            <div class="form-group col-md-12">
              <label class="control-label" for="depart">Départ</label>
              <div class="controls">
                <input type="text" name="depart" id="depart"
                  class="form-control" />
              </div>
            </div>
            <!-- Select Basic -->
            <div class="form-group col-md-12">
              <label class="control-label" for="arrivee">Arrivée</label>
              <div class="controls">
                <input type="text" name="arrivee" id="arrivee"
                  class="form-control" />
              </div>
            </div>
            <!-- Button -->
            <div class="control-group">
              <div class="controls">
                <button id="searchtrain" name="searchtrain"
                  class="btn btn-primary">Rechercher</button>
              </div>
            </div>
          </fieldset>
          
          <br />
          
          <fieldset>
            <legend>Rechercher un membre</legend>
            <div class="form-group col-md-12">
              <input type="text" name="user" placeholder="Membre"
                class="form-control" />
            </div>
            <!-- Button -->
            <div class="control-group">
              <div class="controls">
                <button id="searchuser" name="searchuser"
                  class="btn btn-primary">Rechercher</button>
              </div>
            </div>
          </fieldset>
        </form>
        
      </div>

      <div class="col-md-3">
        <div id="chatbar">
          <ul class="nav nav-tabs" role="tablist">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Discussions <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu"></ul>
            </li>
          </ul>
          <div id="chats">
            <input type="hidden" class="login" value='<c:out value="${login}" />' />
          </div>
        </div>
      </div>

    </div>
    <div id="resultat" class="row">
      <div class="col-md-offset-2 col-md-8">
        <table id="trains" class="table table-striped table-hover">
          <thead>
            <tr>
              <th>Nom</th>
              <th>Horaire</th>
              <th>Code</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
      </div>
    </div>
    <br />

    <div class="row">
      <div class="col-md-offset-2 col-md-8">
        <table class="table table-striped table-hover" id="users">
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
          
            <c:forEach var="user" items="${usersConnectedList}">
            
              <tr>
                <td class="login"><c:out value="${user.login}" /></td>
                <td><c:if test="${user.sexe eq 'male'}">Masculin</c:if>
                  <c:if test="${user.sexe eq 'female'}">Féminin</c:if></td>
                <td><c:out value="${user.presentation}" /></td>
                <td>
                  <ul>
                    <c:forEach var="interest" items="${user.interests}">
                      <li><c:out value="${interest}" /></li>
                    </c:forEach>
                  </ul>
                </td>
                
                <td>

                  <div class="chooseReceiver">
                    <button type="button" class="btn btn-primary btn-xs talk">Chatter</button>
                    <input type="hidden" class="destinataire" value='<c:out value="${user.login}" />' />
                  </div>

                </td>
              </tr>
            </c:forEach>
             
          </tbody>
        </table>
      </div>
    </div>
    
  </div>

  <script type="text/javascript" src="/_ah/channel/jsapi"></script>
  <script type="text/javascript" src="../js/jquery.min.js"></script>
  <script type="text/javascript" src="../js/jquery-ui.min.js"></script>
  <script type="text/javascript" src="../js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/bootstrapValidator.min.js"></script>
  <script type="text/javascript" src="../js/javascript.js"></script>
  <script type="text/javascript" src="../js/autocompleter.js"></script>
  <script type="text/javascript" src="../js/results.js"></script>
</body>
</html>