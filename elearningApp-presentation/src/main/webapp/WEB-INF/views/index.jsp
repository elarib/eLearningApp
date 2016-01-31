<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script src="<c:url value="/resources/js/myjs.js" ></c:url>">
	
</script>
</head>
<body>
	<h2>Prof</h2>
	<table>
		<tr>
			<td>Ajouter un cours</td>
			<td><input type="button"
				onclick="location.href='<c:url value = '/prof/cours/AccesPageajoutCours'/>'"
				value="Ajouter un cours"></td>
		</tr>
		<tr>
			<td>Gérer les cours qu'il a ajoutés</td>
			<td><input type="button"
				onclick="location.href='<c:url value = '/prof/cours/voirTousLesCours'/>'"
				value="Voir tous les cours"></td>
		</tr>
	</table>
	
	<h2>Apprenant</h2>
	<table>
		
		<tr>
			<td>Voir tous les cours publiques</td> <!--En parcourant les cours pub il peut
			s'inscrire à un cours  -->
			<td><input type="button"
				onclick="location.href='<c:url value = '/apprenant/voirCoursPubliques'/>'"
				value="Voir tous les cours publiques"></td>
		</tr>
		
		<tr>
			<td>Voir tous les cours auquels il est inscrit</td>
			<td><input type="button"
				onclick="location.href='<c:url value = '/apprenant/voirTousLesCoursAutorises'/>'"
				value="Voir tous les cours autorsiés"></td>
		</tr>
		
		
		
	</table>



</body>
</html>
