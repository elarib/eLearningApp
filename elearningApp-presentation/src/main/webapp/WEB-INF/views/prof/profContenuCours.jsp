<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/simple-line-icons/css/simple-line-icons.css">
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
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/style.css">

<!-- Head Libs -->
<script
	src="/elearningApp-presentation/resources/vendor/modernizr/modernizr.js"></script>
<script
	src="/elearningApp-presentation/resources/vendor/jquery/jquery.js"></script>
<script
	src="/elearningApp-presentation/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="../tags/header.jsp" %>
	<div class="container ">

		<div class="row form-style1 ">
			<div class="col-md-12">
				<div class="portfolio-title">
					<div class="row form-inside">
						<div class="portfolio-nav-all col-md-1">
							<a href="/elearningApp-presentation/prof/cours/voirTousLesCours"
								data-tooltip data-original-title="Back to list"><i
								class="fa fa-th"></i></a>
						</div>
						<div class="col-md-9 center ">
							<h2 class="mb-none">${cours.name}</h2>
						</div>
						<div class="col-md-2">
							<i class="fa fa-calendar" style="color: blue;"></i> ${cours.dateAjout}
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row form-style2 form-inside">
			<div class="col-md-12">
				<div class="portfolio-info">
					<div class="row">
						<div class="col-md-12 center">
							<ul>
								<li><i class="fa fa-tags"></i> <a href="#">${motCle1}</a>,
									<a href="#">${motCle2}</a></li>
							</ul>
						</div>
					</div>
				</div>
				<h4 class="heading-primary">
					<strong>Description</strong>
				</h4>
				<p class="mt-xlg">${cours.description}</p>
				<div class="row">
					<ul class="portfolio-details">

						<li class="col-lg-6">
							<h4 class="heading-primary">
								<strong>Prerequis:</strong>
							</h4>
							<ul class="list list-inline list-icons">
								<li>${cours.prerequis}</li>
							</ul>
						</li>
						<li class="col-log-6">
							<h4 class="heading-primary">
								<strong>Objectifs:</strong>
							</h4>
							<ul class="list list-inline list-icons">
								<li>${cours.objectifs}</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">

				<h4 class="mb-md text-uppercase" style="margin-left:3%;">
					<strong>Les chapitres</strong>
				</h4>

				<div class="row form-style form-inside">

					<ul class="portfolio-list">
						<c:forEach var="chapitre" items="${chapitresCours}">
							<li class="col-md-3 col-sm-6 col-xs-12">
								<div class="portfolio-item">
									<div class="chapter-box">
										<h4>${chapitre.ordreDuChapitre }
											<strong>${chapitre.nom}</strong>
										</h4>
										<p>${chapitre.description }</p>
										<f:form method="get"
											action="/elearningApp-presentation/prof/cours/chapitres/contenuChapitre">
											<input type=hidden id="thisField" name="chapitre"
												value="${chapitre.id }" />
											<td><button type="submit" class="btn btn-sm btn-primary">
													voir le contenu</button></td>
										</f:form>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>