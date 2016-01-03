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

	<div class=" col-md-3">
		<img
			src="<c:url value="/resources/img/professionalisme.jpg" ></c:url>"
			alt="Responsive image" height="98%" width="98%">
	</div>
	<div class=" col-md-7">
		<div class="row">
			<f:form modelAttribute="myModel" method="post" action="ajoutCours">
				<div class="form-group">

					<label for="name">Titre :</label>
					<f:input path="name" id="name" class="form-control"
						placeholder="Titre" />
					<f:errors path="name" style="color : red;"></f:errors>
				</div>

				<div class="form-group">
					<label for="desc">Description :</label>
					<f:textarea path="description" id="desc" class="form-control"
						placeholder="description" />
					<f:errors path="description" style="color: red;"></f:errors>
				</div>

				<div class="form-group">
					<label for="categorieName">Catégorie :</label>
					<f:select path="categorieName" id="categorieName"
						class="form-control">
						<f:options items="${myModel.allCategoriesNames}" />
					</f:select>
					<f:errors path="categorieName" style="color: red;"></f:errors>
				</div>

				<div class="form-group">
					<label for="motsClesChoisis">Mots Clés :</label>
					<f:select path="motsClesChoisis" multiple="true"
						id="motsClesChoisis" class="form-control">
						<f:options items="${myModel.allmotsCles}" />
					</f:select>
					<f:errors path="motsClesChoisis" style="color: red;"></f:errors>
				</div>

				<div class="form-group">
					<label for="prerequis">Prerequis :</label>
					<f:textarea path="prerequis" rows="5" cols="30" id="prerequis"
						class="form-control" />
					<f:errors path="prerequis" style="color: red;"></f:errors>
				</div>

				<div class="form-group">
					<label>Objectifs :</label>
					<f:textarea path="objectifs" rows="5" cols="30" id="objectifs"
						class="form-control" />
					<f:errors path="objectifs" style="color: red;"></f:errors>
				</div>

				<div class="form-group">
					<input type="submit" value="ajouter le cours"
						class="btn btn-primary" />
				</div>
			</f:form>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>
</html>