<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A propos</title>
<%@include file="tags/resourcesfiles.jsp"%>
</head>
<body>
	<%@include file="tags/header.jsp"%>


    <header id="head" class="secondary">
        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <h1>A propos</h1>
                </div>
            </div>
        </div>
    </header>

    <!-- container -->
    <section class="container">
        <div class="row">
            <!-- main content -->
            <section class="col-sm-8 maincontent">
                <h3>eLearning INPT</h3>
                <p>
                    <img src="<c:url value="/resources/img/about.jpg" />" alt="" class="img-rounded pull-right" width="300">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eveniet, consequuntur eius repellendus eos aliquid molestiae ea laborum ex Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eveniet, consequuntur eius repellendus eos aliquid molestiae ea laborum ex quibusdam laudantium voluptates placeat consectetur quam aliquam beatae soluta accusantium iusto nihil nesciunt unde veniam magnam repudiandae sapiente.
                </p>
                <p>Consectetur adipisicing elit. Eveniet, consequuntur eius repellendus eos aliquid molestiae ea laborum ex quibusdam laudantium voluptates placeat consectetur quam aliquam beatae soluta accusantium iusto nihil nesciunt unde veniam magnam repudiandae sapiente.</p>
                <h3>Nos Réalisations</h3>
                <strong>2015</strong>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eveniet, consequuntur eius repellendus eos aliquid molestiae ea laborum ex quibusdam laudantium voluptates placeat consectetur quam aliquam beatae soluta accusantium iusto nihil nesciunt unde veniam magnam repudiandae sapiente. consequuntur eius repellendus eos aliquid molestiae ea laborum ex quibusdam laudantium voluptates placeat consectetur quam aliquam!</p>
                <strong>2016</strong>
                </section>
            <!-- /main -->

            <!-- Sidebar -->
            <aside class="col-sm-4 sidebar sidebar-right">

                <div class="panel">
                    <h4>Nos Derniers Cours</h4>
                    <ul class="list-unstyled list-spaces">
                        <li><a href="">Cours 1</a><br>
                            <span class="small text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi, laborum.</span></li>
                        <li><a href="">Cours 2</a><br>
                            <span class="small text-muted">Consequuntur eius repellendus eos aliquid molestiae ea laborum ex quibusdam</span></li>
                        <li><a href="">Cours 3</a><br>
                            <span class="small text-muted">Eveniet, consequuntur eius repellendus eos aliquid molestiae ea</span></li>
                       </ul>
                </div> 

            </aside>
            <!-- /Sidebar -->

        </div>
    </section>
   
    <footer id="footer">
		<div class="container">
			<div class="social text-center">
				<a href="#"><i class="fa fa-twitter"></i></a>
				<a href="#"><i class="fa fa-facebook"></i></a>
				<a href="#"><i class="fa fa-dribbble"></i></a>
				<a href="#"><i class="fa fa-flickr"></i></a>
				<a href="#"><i class="fa fa-github"></i></a>
			</div>

			<div class="clear"></div>
			<!--CLEAR FLOATS-->
		</div>
		<div class="footer2">
			<div class="container">
				<div class="row">

					

					<div class="col-md-6 panel">
						<div class="panel-body">
							<p class="text-right">
								Copyright &copy; 2016. eLearning INPT
							</p>
						</div>
					</div>

				</div>
				<!-- /row of panels -->
			</div>
		</div>
	</footer>

</body>
</html>