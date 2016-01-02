<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2>Fonctions à développer</h2>
	<table>
		<tr>
			<td>Ajouter un cours</td>
			<td><input type="button"
				onclick="location.href='<c:url value = '/AccesPageajoutCours'/>'"
				value="Ajouter un cours"></td>
		</tr>
		<tr>
			<td>Voir tous les cours</td>
			<td><input type="button"
				onclick="location.href='<c:url value = '/voirTousLesCours'/>'"
				value="Voir tous les cours"></td>
		</tr>
	</table>
</body>
</html>
