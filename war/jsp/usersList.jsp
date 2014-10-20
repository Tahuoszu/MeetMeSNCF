<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des membres</title>
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
</head>
<body>

  <%@ page import="domain.User,java.util.List,java.util.ArrayList"%>
  <%
      // ---------- Pour tester
      List<User> usersList = new ArrayList<User>();
      User u = new User();
      u.setLogin("Titi"); u.setSexe("male");
      u.setPresentation("Blablabladsksjkdjskd skdjskdjskd ksjdksjd");
      List<String> interests = new ArrayList<String>(); interests.add("Musique");interests.add("Cinéma");
      u.setInterests(interests);
      usersList.add(u);
      User u2 = new User();
      u2.setLogin("Tata"); u2.setSexe("female"); u2.setFirstname("Jean");
      u2.setLastname("Dupont");
      u2.setPresentation("sdlhlskdjsk SJlsk ksjdksjd");
      usersList.add(u2);
      User u3 = new User();
      u3.setLogin("Toto"); u3.setSexe("male");
      u3.setPresentation("Blousousous sdlklsdf zkejzk");
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
</div>
</html>