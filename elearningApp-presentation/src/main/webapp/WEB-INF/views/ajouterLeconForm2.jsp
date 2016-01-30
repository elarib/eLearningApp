<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
	<ul class="breadcrumb">
		<li class="active"><a href="index">Home</a></li>
		<li class="active"><a href="#">Profil</a></li>
		<li class="active"><a href="voirTousLesCours">Gérer les cours</a></li>
	</ul>
	<h2>Ajouter une leçon</h2>

	<div>
		<f:form modelAttribute="ajoutLeconModel" method="post"
			action="dbAjoutLecon">
					<td>Titre :</td>
					<td><f:input path="name" /></td>
					<td style="color: red;"><f:errors path="name"></f:errors></td>
				<div class="form-group">
				    <label for="exampleInputEmail1">Titre</label>
				    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
				</div>

				<tr>
					<td>Contenu :</td>
					<td><textarea id="myarea1">Test</textarea></td>
				</tr>


				<tr>
					<td>Lien vidéo :</td>
					<td><f:input path="lienVideo" /></td>
					<td style="color: red;"><f:errors path="lienVideo"></f:errors></td>
				</tr>


				<tr>
					<td><f:input type="text" id="hiddenHTML" path="content"
							hidden="true"></f:input></td>
				<tr>
				<tr>
					<td><input type="submit" value="Ajouter la leçon"
						onclick="get_text()" /></td>
				</tr>

				<tr>
					<td><output style="color: green;">${messageAjoutLecon }</output></td>
				</tr>

			</table>
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