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
		<div class="col-md-2">
			<h2>MENU</h2>
		</div>
		<div class=" col-md-9">
			<h3 class="text-muted">Contenu du chapitre : Toutes les leçons</h3>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>Name</th>
						<th>Lien Vidéo</th>
						<th>Contenu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lecon" items="${leconsDuChapitre}">

						<tr>

							<td>${lecon.id}</td>
							<td>${lecon.nom }
							<td>${lecon.lienVideo}</td>


							<f:form method="get" action="contenuLecon">
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
	</div>
</body>
</html>