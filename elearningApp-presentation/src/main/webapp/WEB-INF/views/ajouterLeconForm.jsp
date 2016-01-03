<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
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
	<h2>Ajouter une leçon</h2>

	<div>
		<f:form modelAttribute="ajoutLeconModel" method="post"
			action="dbAjoutLecon">

			<table>

				<tr>
					<td>Titre :</td>
					<td><f:input path="name" /></td>
					<td style="color: red;"><f:errors path="name"></f:errors></td>
				</tr>


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
					<td>Vidéo :</td>
					<td><iframe width="560" height="315"
							src="https://www.youtube.com/embed/eOOyxSMI0aE" frameborder="0"
							allowfullscreen></iframe></td>
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