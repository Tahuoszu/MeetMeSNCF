<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
  <div class="container">
    <c:if test="${empty login}">
      <ul class="nav navbar-nav navbar-right">
        <li ${(param.active eq 'login') ? 'class="active"' : ''}><a
          href="/login">Connexion</a></li>
        <li ${(param.active eq 'register') ? 'class="active"' : ''}><a
          href="/register">Inscription</a></li>
      </ul>
    </c:if>
    <c:if test="${not empty login}">
      <ul class="nav navbar-nav navbar-left">
        <li ${(param.active eq 'search') ? 'class="active"' : ''}><a
          href="/jsp/search.jsp">Accueil</a></li>
        <li ${(param.active eq 'contact') ? 'class="active"' : ''}><a
          href="/contact">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/logout">Deconnexion</a></li>
        <li><h4><span class="label label-primary">${login}</span></h4></li>
      </ul>
    </c:if>
  </div>
</nav>
