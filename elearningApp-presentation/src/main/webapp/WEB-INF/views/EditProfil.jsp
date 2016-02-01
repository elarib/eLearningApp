<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />">

<%@include file="tags/resourcesfiles.jsp"%>
<style type="text/css">
body {
	background: #F1F3FA;
}

/* Profile container */
.profile {
	margin: 20px 0;
}

/* Profile sidebar */
.profile-sidebar {
	padding: 20px 0 10px 0;
	background: #fff;
}

.profile-userpic img {
	float: none;
	margin: 0 auto;
	width: 50%;
	height: 50%;
	-webkit-border-radius: 50% !important;
	-moz-border-radius: 50% !important;
	border-radius: 50% !important;
}

.profile-usertitle {
	text-align: center;
	margin-top: 20px;
}

.profile-usertitle-name {
	color: #5a7391;
	font-size: 16px;
	font-weight: 600;
	margin-bottom: 7px;
}

.profile-usertitle-job {
	text-transform: uppercase;
	color: #5b9bd1;
	font-size: 12px;
	font-weight: 600;
	margin-bottom: 15px;
}

.profile-userbuttons {
	text-align: center;
	margin-top: 10px;
}

.profile-userbuttons .btn {
	text-transform: uppercase;
	font-size: 11px;
	font-weight: 600;
	padding: 6px 15px;
	margin-right: 5px;
}

.profile-userbuttons .btn:last-child {
	margin-right: 0px;
}

.profile-usermenu {
	margin-top: 30px;
}

.profile-usermenu ul li {
	border-bottom: 1px solid #f0f4f7;
}

.profile-usermenu ul li:last-child {
	border-bottom: none;
}

.profile-usermenu ul li a {
	color: #93a3b5;
	font-size: 14px;
	font-weight: 400;
}

.profile-usermenu ul li a i {
	margin-right: 8px;
	font-size: 14px;
}

.profile-usermenu ul li a:hover {
	background-color: #fafcfd;
	color: #5b9bd1;
}

.profile-usermenu ul li.active {
	border-bottom: none;
}

.profile-usermenu ul li.active a {
	color: #5b9bd1;
	background-color: #f6f9fb;
	border-left: 2px solid #5b9bd1;
	margin-left: -2px;
}

/* Profile Content */
.profile-content {
	padding: 20px;
	background: #fff;
	min-height: 460px;
}
</style>
</head>
<body>
	<%@include file="tags/header.jsp"%>





	<div class="container">
		<div class="row profile">
			<div class="col-md-3">
				<div class="profile-sidebar">
					<!-- SIDEBAR USERPIC -->
					<div class="profile-userpic">
						<img src="<c:url value="/resources/img/avatar.png"/>"
							class="img-responsive" alt="">
					</div>
					<!-- END SIDEBAR USERPIC -->
					<!-- SIDEBAR USER TITLE -->
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">${sessionScope.user.nom }
							${sessionScope.user.prenom }</div>
						<div class="profile-usertitle-job">${role }</div>
					</div>
					<!-- END SIDEBAR USER TITLE -->
					<!-- SIDEBAR BUTTONS -->
					<div class="profile-userbuttons">
						<a href="<c:url value="/logout" />" class="btn btn-danger btn-sm">Log
							out</a>
					</div>
					<!-- END SIDEBAR BUTTONS -->
					<!-- SIDEBAR MENU -->
					<div class="profile-usermenu">
						<ul class="nav">
							<li class="active"><a href="<c:url value="/profil "/>">
									<i class="glyphicon glyphicon-home"></i> Overview
							</a></li>
							<li><a href="<c:url value="/profil/edit" />"> <i
									class="glyphicon glyphicon-user"></i> Changer votre infos
							</a></li>
							<li><a href="#" target="_blank"> <i
									class="glyphicon glyphicon-ok"></i> Cours privés
							</a></li>
							<li><a href="#"> <i class="glyphicon glyphicon-flag"></i>
									Aide
							</a></li>
						</ul>
					</div>
					<!-- END MENU -->
				</div>
			</div>
			<div class="col-md-9">
				<div class="profile-content">
					<form class="form-horizontal" method="POST">


						<fieldset>

							<!-- Form Name -->

	
								<c:if test="${good !=null }">
									<div class="row">
										<div class="col-sm-4"></div>
										<div class="col-sm-4">
											<div class="alert alert-success">Infos Sauvegardé avec
												success.</div>
										</div>
										<div class="col-sm-4"></div>
									</div>
								</c:if>
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="Nom">Nom</label>
									<div class="col-md-4">
										<input id="Nom" name="nom" type="text"
											value="${sessionScope.user.nom}"
											class="form-control input-md"> <span
											class="help-block"></span>
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="prenom">Prenom</label>
									<div class="col-md-4">
										<input id="prenom" name="prenom" type="text"
											value="${sessionScope.user.prenom}"
											class="form-control input-md"> <span
											class="help-block"></span>
									</div>
								</div>

								<!-- Password input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="motdepasse">Mot
										de Passe</label>
									<div class="col-md-4">
										<input id="motdepasse" name="motdepasse" type="password"
											placeholder="Mot de Passe" class="form-control input-md">
										<span class="help-block"></span>
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="date">Date
										de Naissance</label>
									<div class="col-md-4">
										<input id="date" name="date" type="text"
											value="${sessionScope.user.dateNaissance}"
											class="form-control input-md"> <span
											class="help-block"></span>
									</div>

								</div>

								<!-- Button -->
								<div class="form-group">
									<div class="form-group"">
										<center>
											<button id="submit" name="submit" class="btn btn-primary">Enregistrer</button>
										</center>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</fieldset>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>