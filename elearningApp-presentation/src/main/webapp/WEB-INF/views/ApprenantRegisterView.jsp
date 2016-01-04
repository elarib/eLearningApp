<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
		<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Oleo+Script:400,700'>
        <link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />'>
        <link rel="stylesheet" href='<c:url value="/resources/css/style.css" />'>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
         <script src="//code.jquery.com/jquery-1.10.2.js"></script>
 		 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>

 <div class="register span6" style=" position: absolute;  left: 0; right: 0; margin: auto; ">
                 <form:form commandName="user" method="POST">
                 		
                        <h2>Register To <span class="red"><strong>Online Learning</strong></span></h2>
                        <label for="nom">Nom</label>
                        <form:input path="nom" placeholder="Entrer votre nom" />
                        <form:errors path="nom"/>
                        
                         <label for="prenom">Prenom</label>
                         <form:input path="prenom" placeholder="Entrer votre prenom" />
                        <form:errors path="prenom"/>
                        
                         <label for="userName">Nom d'utilisateur</label>
                         <form:input path="userName" placeholder="Entrer votre username" />
                        
                        <label for="email">Email</label>
                        <form:input path="email" placeholder="Entrer votre email" />
                        <form:errors path="email"/>
                        
                        <label for="motDePasse">Mot de passe</label>
                        <form:password path="motDePasse" placeholder="Entrer votre Mot de passe" />
                        <form:errors path="motDePasse"/>
                        
                        <label for="motDePasseConfirm">Confirmer votre Mot de passe</label>
                        <form:password path="motDePasseConfirm" placeholder="Confirmer votre Mot de Passe" />
                        <form:errors path="motDePasseConfirm"/>
                        
                        <label for="dateNaissance">Date Naissance</label>
                        <form:input path="dateNaissance"  id="datepicker" placeholder="Entrer votre Date Naissance" />
 						<form:errors path="dateNaissance"/>
 	<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
                        <button type="submit">S'inscrire</button>
                   </form:form>
           
                </div>



<script>
 $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</body>
</html>