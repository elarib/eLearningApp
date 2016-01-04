<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.1/css/bootstrap-datepicker.css" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.1/js/bootstrap-datepicker.min.js"></script>


</head>
<body>


	<div id="signupbox" style="margin-top: 50px"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign Up</div>
				<div
					style="float: right; font-size: 85%; position: relative; top: -10px">
					<a id="login" href="<c:url value="/login" />">Log In</a>
				</div>
			</div>
			<div class="panel-body">
				<form:form  id="signupform" class="form-horizontal"
					role="form" commandName="user" method="POST">

					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>



					<div class="form-group">
						<label for="nom" class="col-md-3 control-label">Nom</label>
						<div class="col-md-9">
							<form:input path="nom" cssClass="form-control"
								placeholder="Entrer votre nom" />
							<form:errors cssStyle="  display: block;" cssClass="alert alert-danger"  path="nom" />

						</div>
					</div>




					<div class="form-group">
						<label for="prenom" class="col-md-3 control-label">Prenom</label>
						<div class="col-md-9">
							<form:input cssClass="form-control" path="prenom"
								placeholder="Entrer votre prenom" />
							<form:errors cssStyle="  display: block;" cssClass="alert alert-danger" path="prenom" />

						</div>
					</div>




					<div class="form-group">
						<label for="userName" class="col-md-3 control-label">userName</label>
						<div class="col-md-9">
							<form:input cssClass="form-control" path="userName"
								placeholder="Entrer votre username" />

						</div>
					</div>





					<div class="form-group">
						<label for="email" class="col-md-3 control-label">email</label>
						<div class="col-md-9">

							<form:input cssClass="form-control" path="email"
								placeholder="Entrer votre email" />
							<form:errors cssStyle="  display: block;" cssClass="alert alert-danger" path="email" />
						</div>
					</div>




					<div class="form-group">
						<label for="motDePasse" class="col-md-3 control-label">Mot
							de Passe</label>
						<div class="col-md-9">

							<form:password cssClass="form-control" path="motDePasse"
								placeholder="Entrer votre Mot de passe" />
							<form:errors cssStyle="  display: block;" cssClass="alert alert-danger" path="motDePasse" />
						</div>
					</div>



					<div class="form-group">
						<label for="motDePasseConfirm" class="col-md-3 control-label">Confirmer
							votre Mot de passe</label>
						<div class="col-md-9">
							<form:password cssClass="form-control" path="motDePasseConfirm"
								placeholder="Confirmer votre Mot de Passe" />
							<form:errors cssStyle="  display: block;" cssClass="alert alert-danger" path="motDePasseConfirm" />

						</div>
					</div>



					<div class="form-group">
						<label for="dateNaissance" class="col-md-3 control-label">Date
							Naissance</label>
						<div class="col-md-9">

							<form:input cssClass="form-control" path="dateNaissance"
								id="datePickerBootstrap"
								placeholder="Entrer votre Date Naissance" />
									
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />






					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info">
								<i class="icon-hand-right"></i> &nbsp Sign Up
							</button>
							<span style="margin-left: 8px; margin-right: 8px;">or</span>
							<button id="btn-fbsignup" type="button" class="btn btn-primary">
								<i class="icon-facebook"></i>   Sign Up with Facebook
							</button>

						</div>
					</div>



				</form:form>
			</div>
		</div>




	</div>
	</div>

	<script>
		$(function() {
			$("#datepicker").datepicker();
		});

		$('#datePickerBootstrap').datepicker({
			format : "dd/mm/yyyy",
			startDate : "01/01/1990",
			language : "fr",
			calendarWeeks : true,
			defaultViewDate : {
				year : 1977,
				month : 04,
				day : 25
			}
		});
	</script>
</body>
</html>