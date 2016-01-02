<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<body>
	<h2>Ajouter cours</h2>
	<div>
		<f:form modelAttribute="myModel" method="post" action="ajoutCours">
			<table>
				<tr>
					<td>Titre :</td>
					<td><f:input path="name" /></td>
					<td style="color: red;"><f:errors path="name"></f:errors></td>
				</tr>
				<tr>
					<td>Description :</td>
					<td><f:textarea path="description" /></td>
					<td style="color: red;"><f:errors path="description"></f:errors></td>
				</tr>
				<tr>
					<td>Categorie</td>
					<td><f:select path="categorieName">
							<f:option value="NONE" label="--- Select ---" />
							<f:options items="${myModel.allCategoriesNames}" />
						</f:select></td>
					<td><f:errors path="categorieName"></f:errors></td>
				</tr>
				<tr>
					<td>Mots Clés :</td>
					<td><f:select path="motsClesChoisis" multiple="true">
							<f:option value="NONE" label="--- Select ---" />
							<f:options items="${myModel.allmotsCles}" />
						</f:select></td>
					<td><f:errors path="motsClesChoisis"></f:errors></td>
				</tr>

				<tr>
					<td>Prerequis :</td>
					<td><f:textarea path="prerequis" rows="5" cols="30" /></td>
				</tr>
				
				<tr>
					<td>Objectifs :</td>
					<td><f:textarea path="objectifs" rows="5" cols="30" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="OK" /></td>
				</tr>
			</table>
		</f:form>
	</div>

</body>
</html>