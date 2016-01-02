<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Affichage cours ajouté</h2>
	<div>
		<table>
			<tr>
				<td>Title is :</td>
				<td>${cours.name }</td>
			</tr>
			<tr>
				<td>Description is :</td>
				<td>${cours.description }</td>
			</tr>
			<tr>
				<td>Added on :</td>
				<td>${cours.dateAjout }</td>
			</tr>
			<tr>
				<td>Categorie :</td>
				<td>${cours.categorie.nom }</td>
			</tr>
			<tr>
				<td>Prerequis :</td>
				<td>${cours.prerequis }</td>
			</tr>
			<tr>
				<td>Objectifs :</td>
				<td>${cours.objectifs }</td>
			</tr>
		</table>
		<%-- 		${cours.motsCles }
 --%>
	</div>
</body>
</html>