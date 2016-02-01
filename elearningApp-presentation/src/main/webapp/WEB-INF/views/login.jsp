<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>


<link rel="stylesheet"
	href="<c:url value="resources/vendor/font-awesome/css/font-awesome.min.css" />"></head>
<%@include file="tags/resourcesfiles.jsp"%>
</head>
<body>
	<%@include file="tags/header.jsp"%>

	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Login</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Password Oublié ?</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>


					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" dclass="form-horizontal"
						role="form">
						<c:if test="${logout != null}">
							<div class="alert alert-success">
							 ${logout}
							</div>
						
						</c:if>
						
						<c:if test="${error != null}">
							<div class="alert alert-danger">
								<c:if test="${not empty error }">
									<div class="errorblock">
										Vous n'avez pas pu se connecter <br /> Cause: ${error}
									</div>
									<div>
										<c:if test="${ error eq 'badCredentials'}">
											<p>
												<a href='<c:url value="/recupererMotdePasse" />'>Mot de passe Oublié</a>
											</p>
										</c:if>
										<c:if test="${ error eq 'noSuchUserFOund'}">
											<p>
												<a href='<c:url value="/signup" />'>Créer un compte </a>
											</p>
										</c:if>
										<c:if test="${ error eq 'accountDisabled'}">
											<p>
												<a href='<c:url value="/reenvoyerConfirmation" />'>Veuillez
													l'activez ou réenvoyer le lien de confirmation </a>
											</p>
										</c:if>


									</div>
								</c:if>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>Vous avez été connecté avec succés</p>
							</div>
						</c:if>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								class="form-control" id="username" name="ssoId"
								placeholder="Enter Username" required>
						</div>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Enter Password" required>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div style="margin-top: 10px;display: flex;" class="form-group" >
							<!-- Button -->

							<div class="col-sm-12 controls">
								<input type="submit" class="btn btn-success" value="Login" />
							</div>
							
							<div class="col-md-12 control">
								<div
									style=" width: 217px;padding-top: 35px; font-size: 85%">
									Vous avez pas un compte <a href="<c:url value="/signup" />"> Enregistrer ici
									</a>
								</div>
							</div>
						</div>


						
					</form>
				</div>
			</div>
		</div>
</body>
</html>