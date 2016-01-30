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
					<td><f:form action="AccesPageajoutCours">
							<input type="submit" value="ajouter un cours"
								class="btn btn-warning" style="float: right;" />
						</f:form></td>
				</tr>
			</table>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>Titre</th>
						<th>Date d'ajout</th>
						<th>Contenu</th>
						<th>Nouveau chapitre</th>
						<th>Rendre publique</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cours" items="${tousLesCours}">
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

							<f:form>
								<input type=hidden id="thisField" name="cours"
									value="${cours.id }" />
								<!-- <td><input type="submit" value="ajouter un chapitre" 
										class="btn btn-link" /></td> -->
								<td>
									<button type="button" onclick="setIdModal(${cours.id })"
										class="btn btn-link" data-toggle="modal"
										data-target="#myModal">ajouter un chapitre</button>
								</td>
							</f:form>
							<f:form method="get" action="rendrePublique">
								<input type=hidden id="thisField" name="cours"
									value="${cours.id }" />
								<td><input type="submit" value="Rendre Publique"
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
						<h4 class="modal-title">Ajout d'un chapitre</h4>
					</div>
					<div class="modal-body">

						<f:form modelAttribute="ajoutChapModel" method="post"
							action="ajoutChapitre">

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


							<input type="text" id="hiddenId" name="cours" hidden />
							<button type="submit" class="btn btn-primary">Ajouter le
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

		<output style="color: green;" class="bg-success">${messageAjoutChap }</output>
		<output style="color: red;">${messageAjoutChapError }</output>

		<table class="table">
			<c:forEach var="error" items="${errors}">

				<tr class="small bg-danger">
					<td class="text-danger">${error.field }:</td>
					<td>${error.defaultMessage }</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<div class=" col-md-1"></div>



	<script>
	var setIdModal=function(id){
		$('#hiddenId').val(id);
	}
	</script>

</body>
</html>