<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/bootstrap/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="/elearningApp-presentation/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

</head>

<body>
	<%@ include file="headerProf.jsp"%>
	<ul class="breadcrumb">
		<li class="active"><a href="index">Home</a></li>
		<li class="active"><a href="#">Profil</a></li>
		<li class="active">Gérer les cours</li>
	</ul>
	<!-- MESSAGES -->
	<c:if test="${not empty messageAjoutChap}">
		<output style="color: green;" class="bg-success">${messageAjoutChap }</output>
	</c:if>
	<c:if test="${not empty messageAjoutChapError }">
		<output style="color: red;">${messageAjoutChapError }</output>
	</c:if>

	<table class="table">
		<c:forEach var="error" items="${errors}">

			<tr class="small bg-danger">
				<td class="text-danger">${error.field }:</td>
				<td>${error.defaultMessage }</td>
			</tr>
		</c:forEach>
	</table>
	<!-- END MESSAGES -->
	<div class="row" style="overflow: auto; margin: 50px auto">
		<c:forEach var="cours" items="${tousLesCours}">
			<div class="col-lg-4 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">${cours.name}</div>
					<div class="panel-body">
						<p >${cours.description }</p>
						<br/>
						<div class="row">
							<div style="position: absolute; bottom: 10%; right: 40%;">
								<f:form method="get" action="/elearningApp-presentation/prof/cours/contenuCours">
									<input type=hidden id="thisField" name="cours"
										value="${cours.id }" />
									<button type="submit" class="btn btn-warning btn-sm"
										title="voir le contenu">
										<span class="glyphicon glyphicon-option-horizontal"></span>
									</button>
								</f:form>
							</div>
							<div style="position: absolute; bottom: 10%; right: 25%;">
								<f:form>
									<input type=hidden id="thisField" name="cours"
										value="${cours.id }" />

									<button type="button" onclick="setIdModal(${cours.id })"
										class="btn btn-primary btn-sm " data-toggle="modal"
										data-target="#myModal" title="Ajouter un chapitre">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
								</f:form>
							</div>
							<div style="position: absolute; bottom: 10%; right: 10%;">
								<f:form method="get"
									action="/elearningApp-presentation/prof/cours/rendrePublique">
									<input type=hidden id="thisField" name="cours"
										value="${cours.id }" />
									<td><button type="submit" value="Rendre Publique"
											class="btn btn-success btn-sm"
											title="Rendre le cours publique">
											<span class="glyphicon glyphicon-globe "></span>
										</button></td>
								</f:form>
							</div>
						</div>

					</div>
				</div>
			</div>
		</c:forEach>

	</div>


	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Ajout d'un chapitre</h4>
				</div>
				<div class="modal-body">

					<f:form modelAttribute="ajoutChapModel" method="post"
						action="/elearningApp-presentation/prof/cours/chapitres/ajoutChapitre">

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
						<button type="submit" class="btn btn-primary" title="tiiitle">Ajouter
							le chapitre</button>


					</f:form>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end modal -->






	<script>
	var setIdModal=function(id){
		$('#hiddenId').val(id);
	}
	</script>

</body>
</html>