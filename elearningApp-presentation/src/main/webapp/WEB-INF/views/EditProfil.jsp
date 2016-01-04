<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form  method="POST">

		<h2>
			Modifier <span class="red"><strong>Mes Infos</strong></span>
		</h2>
		<h5>*Modifer seul les champs QUE vous voulez</h5>
		<label for="nom">Nom</label> <input type="text" id="nom" name="nom"
			placeholder="Entrer votre Nom" value="${sessionScope.user.nom }" />

		<label for="prenom">Prenom</label> <input type="text" id="prenom"
			name="prenom" placeholder="Entrer votre Prenom"
			value="${sessionScope.user.prenom }" /> <label for="motdepasse">Mot
			De passe</label> <input type="password" id="motdepasse" name="motdepasse"
			placeholder="Entrer votre Nom" " /> <label for="date">Prenom</label>
		<input type="text" id="date" name="date"
			placeholder="Entrer votre date"
			value="${sessionScope.user.dateNaissance }" /> <input type="submit"
			value="Sauvegarder">
			
			<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
			
	</form>






</body>
</html>