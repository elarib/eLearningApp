<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Ajouter un chapitre</h2>
	<div>
		<f:form modelAttribute="myModel" method="post"
			action="modifierChapitre">
			<table>
				<tr>
					<td>Ordre :</td>
					<td><f:input path="ordreDuChapitre" /></td>
					<td style="color: red;"><f:errors path="ordreDuChapitre"></f:errors></td>
				</tr>
				<tr>
					<td>nom du chapitre :</td>
					<td><f:input path="name" /></td>
					<td style="color: red;"><f:errors path="name"></f:errors></td>
				</tr>
				<tr>
					<td>Description :</td>
					<td><f:input path="description" /></td>
					<td style="color: red;"><f:errors path="description"></f:errors></td>
				</tr>

				<tr>
					<td><input type="submit" value="Modifier le chapitre" /></td>
				</tr>
			</table>
		</f:form>
		
	</div>

</body>
</html>