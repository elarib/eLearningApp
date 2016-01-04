<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Profil de ${sessionScope.user.nom } ${sessionScope.user.prenom }</h1>
	<h4>Role : <sec:authentication property="principal.authorities"/></h4>
	<p>
		UserName :
		<sec:authentication property="name" />
	</p>
	<p>Nom : ${sessionScope.user.nom }</p>
	<p>Prenom : ${sessionScope.user.prenom }</p>
	<p>Email : ${sessionScope.user.email }</p>
	<p>Date de naissance : ${sessionScope.user.dateNaissance }</p>
	<p>Date d'Inscription : ${sessionScope.user.dateInscription }</p>


	<p>
		<a href="<c:url value="/profil/edit"/>">Modifier Mes Infos</a>
	</p>


	<a href="<c:url value="/logout"/>">Logout</a>

</body>
</html>