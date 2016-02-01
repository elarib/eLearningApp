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
	<ul class="breadcrumb">
		<li class="active"><a href="index">Home</a></li>
		<li class="active"><a href="#">Profil</a></li>
		<li class="active">Gérer les cours</li>
	</ul>


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
		<c:forEach var="cours" items="${tousLesCoursPubliques}">
			<div class="col-lg-4 col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">${cours.name}</div>
					<div class="panel-body">
						<p>${cours.description }</p>
						<br />
						<div class="row">
							<div style="position: absolute; bottom: 10%; right: 10%;">
								<f:form method="get"
									action="/elearningApp-presentation/prof/cours/contenuCours">
									<input type=hidden id="thisField" name="cours"
										value="${cours.id }" />
									<button type="submit" class="btn btn-warning "
										title="voir le contenu">
										<span class="glyphicon glyphicon-option-horizontal"></span>
									</button>
								</f:form>
							</div>
						</div>

					</div>
				</div>
			</div>
		</c:forEach>

	</div>

	<script>
		var setIdModal = function(id) {
			$('#hiddenId').val(id);
		}
	</script>

</body>
</html>