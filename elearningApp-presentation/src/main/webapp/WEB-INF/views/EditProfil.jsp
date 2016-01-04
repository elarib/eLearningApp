<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier mes Infos</title>
<link rel="stylesheet"
	href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />">
</head>
<body>
	<form class="form-horizontal" method="POST">


		<fieldset>

			<!-- Form Name -->


			<legend>Modifier Mes Infos</legend>
			<c:if test="${good !=null }">
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<div class="alert alert-success">
						Infos Sauvegardé avec success.
					</div>
				</div>
				<div class="col-sm-4"></div>
			</div>
			</c:if>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="Nom">Nom</label>
				<div class="col-md-4">
					<input id="Nom" name="nom" type="text"
						value="${sessionScope.user.nom}" class="form-control input-md">
					<span class="help-block"></span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="prenom">Prenom</label>
				<div class="col-md-4">
					<input id="prenom" name="prenom" type="text"
						value="${sessionScope.user.prenom}" class="form-control input-md">
					<span class="help-block"></span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="motdepasse">Mot
					de Passe</label>
				<div class="col-md-4">
					<input id="motdepasse" name="motdepasse" type="password"
						placeholder="Mot de Passe" class="form-control input-md">
					<span class="help-block"></span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="date">Date de
					Naissance</label>
				<div class="col-md-4">
					<input id="date" name="date" type="text"
						value="${sessionScope.user.dateNaissance}"
						class="form-control input-md"> <span class="help-block"></span>
				</div>

			</div>

			<!-- Button -->
			<div class="form-group">
				<div class="form-group"">
					<center>
						<button id="submit" name="submit" class="btn btn-primary">Enregistrer</button>
					</center>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />


		</fieldset>
	</form>








</body>
</html>