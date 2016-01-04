<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>


	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Forgot password?</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>


					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" dclass="form-horizontal"
						role="form">
						<c:if test="${error != null}">
							<div class="alert alert-danger">
								<c:if test="${not empty error }">
									<div class="errorblock">
										Your Login was unsuccessful. <br /> Caused by : ${error}
									</div>
									<div>
										<c:if test="${ error eq 'badCredentials'}">
											<p>
												<a href='<c:url value="/recupererMotdePasse" />'>Forget
													Password ? </a>
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
								<p>You have been logged out successfully.</p>
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

						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<input type="submit" class="btn btn-success" value="Login" />
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! <a href="<c:url value="/signup" />"> Sign Up Here
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