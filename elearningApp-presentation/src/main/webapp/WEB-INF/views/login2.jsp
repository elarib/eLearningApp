<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
		<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Oleo+Script:400,700'>
        <link rel="stylesheet" href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />'>
        <link rel="stylesheet" href='<c:url value="/resources/css/style.css" />'>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
         <script src="//code.jquery.com/jquery-1.10.2.js"></script>
 		 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
 		 <style type="text/css">
		.errorblock{
			color : #ff0000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding : 8px;
			margin: 16px;
		}
	
	</style>
</head>
<body onload='document.f.j_username.focus();'>
<p>jklkjl</p>



	
 <div class="register span6" style=" position: absolute;  left: 0; right: 0; margin: auto; ">
 
 
 <c:if test="${not empty error }">
		<div class="errorblock">
			Your Login was unsuccessful. <br/>
			Caused by : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
		</div>
	</c:if>
                 <form  name="f" method="POST">
                 		
                        <h2>Login To <span class="red"><strong>Online Learning</strong></span></h2>
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" placeholder="Entrer votre userName" />
                        
                        
                         <label for="password">Mot de Passe</label>
                         <input type="password" id="password" name="password" placeholder="Entrer votre mot de passe" />

 							<input type="reset" name="reset" ></td>

                        <button type="submit">Login</button>
                   </form>
           
                </div>


</body>
</html>