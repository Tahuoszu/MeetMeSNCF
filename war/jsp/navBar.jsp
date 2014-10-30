<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
  <div class="container">
    <ul class="nav navbar-nav navbar-left">
      <li ${(param.active eq 'search') ? 'class="active"' : ''}><a
        href="/jsp/search.jsp">Accueil</a></li>
      <li ${(param.active eq 'contact') ? 'class="active"' : ''}><a
        href="/jsp/contact.jsp">Contact</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li ${(param.active eq 'login') ? 'class="active"' : ''}><a
        href="/jsp/login.jsp">Connexion</a></li>
      <li ${(param.active eq 'register') ? 'class="active"' : ''}><a
        href="/jsp/register.jsp">Inscription</a></li>
    </ul>
  </div>
</nav>
