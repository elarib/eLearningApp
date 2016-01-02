<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Contenu du chapitre "toutes les leçons"</h2>
	<table border="2px;">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Lien Vidéo</td>
			<td>Contenu</td>
		</tr>
		<c:forEach var="lecon" items="${leconsDuChapitre}">

			<tr>

				<td>${lecon.id}</td>
				<td>${lecon.nom }
				<td>${lecon.lienVideo}</td>
				

				<f:form method="get" action="contenuLecon">
					<input type=hidden id="thisField" name="lecon" value="${lecon.id }" />
					<td><input type="submit" value="voir le contenu"/></td>
				</f:form>
				
			</tr>

		</c:forEach>
	</table>
</body>
</html>