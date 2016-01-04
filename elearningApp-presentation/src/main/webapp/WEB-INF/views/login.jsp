<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>

<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>

	<c:if test="${not empty error }">
		<div class="errorblock">
			Your Login was unsuccessful. <br /> Caused by :
			${error}
		</div>
		<div>
		<c:if test="${ error eq 'badCredentials'}">
			<p><a href='<c:url value="/recupererMotdePasse" />'>Forget Password ? </a></p>
		</c:if>
		<c:if test="${ error eq 'noSuchUserFOund'}">
			<p><a href='<c:url value="/signup" />'>Créer un compte </a></p>
		</c:if>
		<c:if test="${ error eq 'accountDisabled'}">
			<p><a href='<c:url value="/reenvoyerConfirmation" />'>Veuillez l'activez ou réenvoyer le lien de confirmation  </a></p>
		</c:if>
	
		
		</div>
	</c:if>
	<div>
		<div>
			<div>
				<div style="text-align: center;">

					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" class="form-horizontal">
						<c:if test="${error != null}">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="username"><i
								class="fa fa-user"></i></label> <input type="text" class="form-control"
								id="username" name="ssoId" placeholder="Enter Username" required>
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><i
								class="fa fa-lock"></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Enter Password" required>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>