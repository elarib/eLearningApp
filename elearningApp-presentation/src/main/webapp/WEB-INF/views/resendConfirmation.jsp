<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



	<h1>Reenvoyer l'email de confirmation</h1>
	<form method="POST">

		<label for="email">Email</label> <input type="email" id="email"
			name="email" placeholder="Entrer votre Email" /> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit">Récuperer</input>
	</form>

</body>
</html>