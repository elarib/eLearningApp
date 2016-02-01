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

<%@include file="resourcesfiles.jsp"%>
<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,900|Oswald);
body{
    padding: 50px 0;
}
.status {
    font-family: 'Source Sans Pro', sans-serif;
}
.status .panel-title {
    font-family: 'Oswald', sans-serif;
    font-size: 72px;
    font-weight: bold;
    color: #fff;
    line-height: 45px;
    padding-top: 20px;
    letter-spacing: -0.8px;
}
</style>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-primary">
					
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-6 col-md-6">
								<a href="#" class="btn btn-danger btn-lg" role="button"><span
									class="glyphicon glyphicon-list-alt"></span> <br />Apps</a> <a
									href="#" class="btn btn-warning btn-lg" role="button"><span
									class="glyphicon glyphicon-bookmark"></span> <br />Bookmarks</a> <a
									href="#" class="btn btn-primary btn-lg" role="button"><span
									class="glyphicon glyphicon-signal"></span> <br />Reports</a> <a
									href="#" class="btn btn-primary btn-lg" role="button"><span
									class="glyphicon glyphicon-comment"></span> <br />Comments</a>
							</div>
							<div class="col-xs-6 col-md-6">
								<a href="#" class="btn btn-success btn-lg" role="button"><span
									class="glyphicon glyphicon-user"></span> <br />Users</a> <a
									href="#" class="btn btn-info btn-lg" role="button"><span
									class="glyphicon glyphicon-file"></span> <br />Notes</a> <a
									href="#" class="btn btn-primary btn-lg" role="button"><span
									class="glyphicon glyphicon-picture"></span> <br />Photos</a> <a
									href="#" class="btn btn-primary btn-lg" role="button"><span
									class="glyphicon glyphicon-tag"></span> <br />Tags</a>
							</div>
						</div>
						<a href="http://www.jquery2dotnet.com/"
							class="btn btn-success btn-lg btn-block" role="button"><span
							class="glyphicon glyphicon-globe"></span> Website</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>