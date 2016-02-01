<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
	<link rel="stylesheet"
	href="/elearningApp-presentation/resources/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/elearningApp-presentation/resources/css/style2.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="/elearningApp-presentation/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="../tags/header.jsp"%>

	<div>
				<div class="form-style form-inside">
					<div style="text-align: center;font-size: 45px">${lecon.nom }</div>
					<br/>
					<br/>
					<p>
					${lecon.content }</p>
					<br/>
					<div style="margin:2% 25%;">
					${lecon.lienVideo}
					</div>

				</div>
	</div>
</body>
</html>