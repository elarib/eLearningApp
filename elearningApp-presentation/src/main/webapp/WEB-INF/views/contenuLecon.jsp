<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Contenu de la le�on</h2>
	<div>
		<table>

			<tr>
				<td>Id is :</td>
				<td>${lecon.id}</td>
			</tr>
			<tr>
				<td>title is :</td>
				<td>${lecon.nom }
			</tr>

			<tr>
				<td>Content is :</td>
				<td>${lecon.content }</td>
			</tr>


			<tr>
				<td>lien vid�o is :</td>
				<td>${lecon.lienVideo}</td>
			</tr>

		</table>

	</div>
</body>
</html>