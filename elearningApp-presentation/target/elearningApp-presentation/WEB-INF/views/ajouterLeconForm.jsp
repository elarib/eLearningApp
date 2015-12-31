<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Ajouter une le�on</h2>
	<div>
		<f:form modelAttribute="ajoutLeconModel" method="post"
			action="dbAjoutLecon">
			<table>
				
				<tr>
					<td>nom de la le�on :</td>
					<td><f:input path="name" /></td>
					<td style="color: red;"><f:errors path="name"></f:errors></td>
				</tr>
				<tr>
					<td>Lien de la vid�o :</td>
					<td><f:input path="lienVideo" /></td>
					<td style="color: red;"><f:errors path="lienVideo"></f:errors></td>
				</tr>

				<tr>
					<td><input type="submit" value="OK" /></td>
				</tr>
			</table>
		</f:form>
		
		 <output style="color: green;" >${messageAjoutLecon }</output>
	</div>

</body>
</html>