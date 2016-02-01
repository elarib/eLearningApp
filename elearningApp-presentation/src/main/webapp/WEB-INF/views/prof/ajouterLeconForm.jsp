<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/style2.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>
	tinymce.init({
		selector : 'textarea',
		mode : "specific_textareas",
		editor_selector : "mceEditor"
	});
</script>
</head>
<body>
	<%@ include file="../tags/header.jsp"%>
	<h2 style="text-align:center">Ajouter une leçon</h2>

	<div class="form-style form-inside">
		<f:form modelAttribute="ajoutLeconModel" method="post"
			action="/elearningApp-presentation/prof/cours/chapitres/lecons/dbAjoutLecon">
				
				<div class="form-group">

					<label for="name">Titre :</label>
					<f:input path="name" id="name" class="form-control"
						placeholder="Titre" />
					<f:errors path="name" style="color : red;"></f:errors>
				</div>
				
				<div class="form-group">

					<label for="name">Contenu :</label>
					<textarea id="myarea1">Test</textarea>
				</div>
				
				<div class="form-group">

					<label for="name">Lien vidéo :</label>
					<f:input path="lienVideo" class="form-control"
						placeholder="lien vidéo" />
					<f:errors path="lienVideo"></f:errors>
				</div>
				
				<f:input type="text" id="hiddenHTML" path="content"
							hidden="true"></f:input>
				<input type="submit" class="btn btn-success" value="Ajouter la leçon"
						onclick="get_text()" />

				<output style="color: green;">${messageAjoutLecon }</output>
		</f:form>

	</div>


	<script type="text/javascript">
		function get_text() {
			var text = tinyMCE.get('myarea1').getContent();
			document.getElementById("hiddenHTML").value = text;

		}
	</script>

</body>
</html>