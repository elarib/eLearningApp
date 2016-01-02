<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Ajouter un chapitre</h2>
	<div>
		<f:form modelAttribute="ajoutChapModel" method="post"
			action="ajoutChapitre">
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
					<td><input type="submit" value="OK" /></td>
				</tr>
			</table>
		</f:form>
		
		 <output style="color: green;" >${messageAjoutChap }</output>
	</div>

</body>
</html>