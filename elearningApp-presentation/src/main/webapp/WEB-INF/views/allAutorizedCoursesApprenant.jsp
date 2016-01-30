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
		<li class="active">Gérer les cours</li>
	</ul>

	<div class="col-md-1"></div>
	<div class=" col-md-10">
		<div class="row" style="height: 70%; overflow: auto;">
			<table class="table">
				<tr>
					<td><h4 class="text-muted" style="font-weight: bold;">Tous
							les cours</h4></td>
				</tr>
			</table>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>Titre</th>
						<th>Date d'ajout</th>
						<th>Contenu</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="cours" items="${tousLesCoursAutorises}">
						<tr>
							<td>${cours.id}</td>
							<td>${cours.name}</td>
							<td>${cours.dateAjout }</td>

							<f:form method="get" action="contenuCours">
								<input type=hidden id="thisField" name="cours"
									value="${cours.id }" />
								<td><input type="submit" value="voir le contenu"
									class="btn btn-link" /></td>
							</f:form>




						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


	</div>
	<table class="table">
		<c:forEach var="error" items="${errors}">

			<tr class="small bg-danger">
				<td class="text-danger">${error.field }:</td>
				<td>${error.defaultMessage }</td>
			</tr>
		</c:forEach>
	</table>


	<div class=" col-md-1"></div>



</body>
</html>