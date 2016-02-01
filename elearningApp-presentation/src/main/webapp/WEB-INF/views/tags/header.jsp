<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- Fixed navbar -->
<div class="navbar navbar-inverse" style="z-index: 9999999;">
	<div class="container">
		<div class="navbar-header">
			<!-- Button for smallest screens -->
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span><span class="icon-bar"></span><span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/" />"> <img
				src="<c:url value="/resources/img/logo.png" />"
				alt="Techro HTML5 template"></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav pull-right mainNav">
				<li class="active"><a href="<c:url value="/" />">Accueil</a></li>
				<li><a href="<c:url value="/apropos" />">A propos</a></li>


				<sec:authorize access="isAnonymous()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Cours <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<c:url value="/apprenant/voirCoursPubliques" />">Cours
									Publiques</a></li>
						
						</ul></li>
				</sec:authorize>
				
				
				<sec:authorize access="hasAnyRole('ROLE_PROF')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Cours <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<c:url value="/prof/cours/AccesPageajoutCours" />">Ajouter Cours</a></li>
							<li><a href="<c:url value="/prof/cours/voirTousLesCours" />">Voir Cours</a></li>
						</ul></li>
				</sec:authorize>

				<sec:authorize access="hasAnyRole('ROLE_APPRENANT')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Cours <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<c:url value="/apprenant/voirCoursPubliques" />">Cours
									Publiques</a></li>
							<li><a href="<c:url value="/apprenant/voirTousLesCoursAutorises" />">Cours Privés</a></li>
						</ul></li>
				</sec:authorize>







				<li><a href="<c:url value="/forum" />">Forum</a></li>


				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> 
							<strong>Votre Espace</strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu" style="background: white;">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong>${sessionScope.user.nom }
													${sessionScope.user.prenom }</strong>
											</p>
											<p class="text-left small">${sessionScope.user.email }</p>
											<p class="text-left">
												<a href="<c:url value="/profil"/> "
													class="btn btn-primary btn-block btn-sm">Modifier vos
													Infos</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/logout" />"
													class="btn btn-danger btn-block">Se déconnecter</a>
											</p>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
				</sec:authorize>



				<sec:authorize access="isAnonymous()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Mon Espace <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="login" />">Login</a></li>
							<li><a href="<c:url value="signup" />">S'inscrire</a></li>
						</ul></li>
				</sec:authorize>
			</ul>


		</div>
		<!--/.nav-collapse -->
	</div>
</div>
<!-- /.navbar -->


