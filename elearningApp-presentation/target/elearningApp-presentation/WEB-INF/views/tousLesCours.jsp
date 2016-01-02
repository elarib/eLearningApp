<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Voir tous les cours</h2>
	<table border="2px;">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Description</td>
			<td>Prerequis</td>
			<td>Objectifs</td>
			<td>Contenu</td>
		</tr>
		<c:forEach var="cours" items="${tousLesCours}">

			<tr>

				<td>${cours.id}</td>
				<td>${cours.name}</td>
				<td>${cours.description }</td>
				<td>${cours.prerequis }</td>
				<td>${cours.objectifs }</td>

				<f:form method="get" action="contenuCours">
					<input type=hidden id="thisField" name="cours" value="${cours.id }" />
					<td><input type="submit" value="voir le contenu"/></td>
				</f:form>



				<f:form method="get" action="ajouterChapitreForm">
					<input type=hidden id="thisField" name="cours" value="${cours.id }" />
					<td><input type="submit" value="ajouter un chapitre" /></td>
				</f:form>
			</tr>

		</c:forEach>
	</table>
</body>
</html>