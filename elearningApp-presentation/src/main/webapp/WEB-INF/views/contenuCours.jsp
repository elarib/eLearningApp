<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>

	<ul class="breadcrumb">
		<li class="active"><a href="index">Home</a></li>
		<li class="active"><a href="#">Profil</a></li>
		<li class="active"><a href="voirTousLesCours">Gérer les cours</a></li>
	</ul>
	<div class="row">
		<div class="col-md-1"></div>
		<div class=" col-md-10">

			<table class="table">
				<tr>
					<td><h4 class="text-muted" style="font-weight: bold;">Contenu du cours</h4></td>
					<td><f:form action="AccesPageModifierCours">
							<input type="submit" value="modifier le cours"
								class="btn btn-warning" style="float: right;" />
						</f:form></td>
				</tr>
			</table>


			<table class="table  table-condensed">
				<tr>
					<td class="bg-primary" style="width: 15%; font-weight: bold;">Titre
						:</td>
					<td>${cours.name }</td>
				</tr>
				<tr>
					<td class="bg-primary" style="width: 15%; font-weight: bold;">Description
						:</td>
					<td>${cours.description }</td>
				</tr>
				<tr>
					<td class="bg-primary" style="width: 15%; font-weight: bold;">Prerequis
						:</td>
					<td>${cours.prerequis }</td>
				</tr>
				<tr>
					<td class="bg-primary"
						style="width: 15%; font-weight: bold; font-style: italic;">Objectifs
						:</td>
					<td>${cours.objectifs }</td>
				</tr>
			</table>
			<h3 class="text-muted">Chapitres du cours :</h3>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="bg-primary">
						<th>ID</th>
						<th>Ordre</th>
						<th>Name</th>
						<th>Description</th>
						<th>Contenu</th>
						<th>nouvelle leçon</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="chapitre" items="${chapitresCours}">

						<tr>

							<td>${chapitre.id}</td>
							<td>${chapitre.ordreDuChapitre }
							<td>${chapitre.nom}</td>
							<td>${chapitre.description }</td>


							<f:form method="get" action="contenuChapitre">
								<input type=hidden id="thisField" name="chapitre"
									value="${chapitre.id }" />
								<td><input type="submit" value="voir le contenu"
									class="btn btn-link" /></td>
							</f:form>

							<f:form method="get" action="ajouterLeconForm">
								<input type=hidden id="thisField" name="chapitre"
									value="${chapitre.id }" />
								<td><input type="submit" value="ajouter une leçon"
									class="btn btn-link" /></td>
							</f:form>
						</tr>

					</c:forEach>
				</tbody>
			</table>

		</div>
		<div class="col-md-1"></div>
	</div>

</body>
</html>