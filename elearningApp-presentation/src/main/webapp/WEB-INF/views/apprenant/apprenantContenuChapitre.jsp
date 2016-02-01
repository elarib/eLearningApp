<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/simple-line-icons/css/simple-line-icons.css" />
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/owl.carousel/assets/owl.carousel.min.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/owl.carousel/assets/owl.theme.default.min.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/magnific-popup/magnific-popup.css">

<!-- Theme CSS -->
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/theme.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/theme-elements.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/theme-blog.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/theme-shop.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/theme-animate.css">

<!-- Skin CSS -->
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/skins/default.css">

<!-- Theme Custom CSS -->
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/custom.css">

<!-- Head Libs -->
<script
	src="/elearningApp-presentation/resources/vendor/modernizr/modernizr.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active"><a
			href="/elearningApp-presentation/prof/cours/index">Home</a></li>
		<li class="active"><a href="#">Profil</a></li>
		<li class="active"><a
			href="/elearningApp-presentation/prof/cours/voirTousLesCours">Gérer
				les cours</a></li>
	</ul>

	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<div class="portfolio-title">
					<div class="row">
						<div class="portfolio-nav-all col-md-1">
							<a href="portfolio-single-project.html" data-tooltip
								data-original-title="Back to list"><i class="fa fa-th"></i></a>
						</div>
						<div class="col-md-10 center">
							<h2 class="mb-none">
								Chapitre ${chapitre.ordreDuChapitre }: <strong>${chapitre.nom}</strong>
							</h2>
						</div>
						<div class="portfolio-nav col-md-1">
							<a href="portfolio-single-project.html"
								class="portfolio-nav-prev" data-tooltip
								data-original-title="Previous"><i class="fa fa-chevron-left"></i></a>
							<a href="portfolio-single-project.html"
								class="portfolio-nav-next" data-tooltip
								data-original-title="Next"><i class="fa fa-chevron-right"></i></a>
						</div>
					</div>
				</div>

				<hr class="tall">
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="portfolio-info">
					<div class="row">
						<div class="col-md-12 center">
							<ul>
								<li></li>
							</ul>
						</div>
					</div>
				</div>
				<h4 class="heading-primary">
					<strong>Description</strong>
				</h4>
				<p class="mt-xlg">${chapitre.description}</p>
				<ul class="portfolio-details">
					<li>
						<h4 class="heading-primary">
							<strong>Lecons:</strong>
						</h4>

						<div class="list list-inline list-icons">
							<c:forEach var="lecon" items="${leconsDuChapitre}">
								<f:form class="col-lg-offset-3 col-lg-6" method="get"
									action="/elearningApp-presentation/apprenant/apprenantContenuLecon">
									<input type=hidden id="thisField" name="lecon"
										value="${lecon.id }" />
									<div class="panel panel-success ">
										<div class="panel-heading">${lecon.nom}
											<button type="submit" class="btn btn-sm btn-primary"
												style="float: right">Voir lecon</button>
										</div>
									</div>
								</f:form>
							</c:forEach>
						</div>
					</li>
				</ul>
			</div>
		</div>
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
						action="/elearningApp-presentation/prof/cours/chapitres/modifierChapitre">

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