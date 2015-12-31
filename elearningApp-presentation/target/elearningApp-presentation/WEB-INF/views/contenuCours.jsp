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
	<div class="row">
		<div class="col-md-2"><h2>MENU</h2></div>
		<div class=" col-md-9">
			<h3 class="text-muted">Contenu du cours "tous les chapitres"</h3>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="success">
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
	</div>
</body>
</html>