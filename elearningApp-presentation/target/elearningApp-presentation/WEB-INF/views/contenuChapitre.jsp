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
					<td><h4 class="text-muted" style="font-weight: bold;">Contenu
							du chapitre</h4></td>
					<td><f:form action="AccesPageModifierChapitre">
							<button type="button" class="btn btn-warning" data-toggle="modal"
								data-target="#myModal" style="float: right;">modifier le chapitre</button>
						</f:form></td>
				</tr>
			</table>


			<table class="table  table-condensed">
				<tr>
					<td class="bg-primary" style="width: 15%; font-weight: bold;">Ordre
						:</td>
					<td>${chapitre.ordreDuChapitre }</td>
				</tr>
				<tr>
					<td class="bg-primary" style="width: 15%; font-weight: bold;">Titre
						:</td>
					<td>${chapitre.nom }</td>
				</tr>
				<tr>
					<td class="bg-primary" style="width: 15%; font-weight: bold;">Description
						:</td>
					<td>${chapitre.description }</td>
			</table>

			<h3 class="text-muted">Contenu du chapitre : Toutes les leçons</h3>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>Titre</th>
						<th>Contenu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lecon" items="${leconsDuChapitre}">

						<tr>

							<td>${lecon.id}</td>
							<td>${lecon.nom }<f:form method="get" action="contenuLecon">
									<input type=hidden id="thisField" name="lecon"
										value="${lecon.id }" />
									<td><input type="submit" value="voir le contenu"
										class="btn btn-link" /></td>
								</f:form>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>


		<!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header bg-warning">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Modification du chapitre</h4>
					</div>
					<div class="modal-body">

						<f:form modelAttribute="myModel" method="post"
							action="modifierChapitre">

							<div class="form-group">
								<label for="ordre">Ordre :</label>
								<f:input path="ordreDuChapitre" id="ordre" class="form-control" />
								<f:errors path="ordreDuChapitre"></f:errors>
							</div>
							<div class="form-group">
								<label for="nom">Nom du chapitre :</label>
								<f:input path="name" class="form-control" id="nom" />
								<f:errors path="name"></f:errors>
							</div>
							<div class="form-group">
								<label for="desc">Description :</label>
								<f:input path="description" class="form-control" id="desc" />
								<f:errors path="description"></f:errors>
							</div>


							<button type="submit" class="btn btn-primary">Modifier le
								chapitre</button>


						</f:form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- end modal -->


		<div class="col-md-1"></div>
	</div>
</body>
</html>