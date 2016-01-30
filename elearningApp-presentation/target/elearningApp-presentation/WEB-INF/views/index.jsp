<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script src="<c:url value="/resources/js/myjs.js" ></c:url>">
	
</script>
</head>
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

	<input type="button" onclick="popup()" value="Click Me!">


</body>
</html>
