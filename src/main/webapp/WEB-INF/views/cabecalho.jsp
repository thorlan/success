<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>

<c:url value="/resources/css" var="cssPath" />

<c:url value="/resources/imagens" var="imgPath" />
<c:url value="/resources/imagens/servicos-fotos" var="imgServicoPath" />
<link rel="stylesheet" href="${cssPath}/styles.css" />
<c:url value="/resources/js" var="jsPath" />
<script type="text/javascript" src="${jsPath}/scripts.js"></script>


<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">


<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<link href="http://fonts.googleapis.com/css?family=Cookie"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Succes Generation</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="https://fonts.googleapis.com/css?family=Dhurjati"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body>

	<div id="navigation" class="navigation">
		<div class ="logo">
			<div class="foo">
				  
				  <span class="letter" data-letter="S">S</span>
				  <span class="letter" data-letter="U">U</span>
				  <span class="letter" data-letter="C">C</span>
				  <span class="letter" data-letter="C">C</span>
				  <span class="letter" data-letter="E">E</span>
				  <span class="letter" data-letter="S">S</span>
				  <span class="letter" data-letter="S">S</span>
				  <br>
				  <span class="letter" data-letter="G">G</span>
				  <span class="letter" data-letter="E">E</span>
				  <span class="letter" data-letter="N">N</span>
				  <span class="letter" data-letter="E">E</span>
				  <span class="letter" data-letter="R">R</span>
				  <span class="letter" data-letter="A">A</span>
				  <span class="letter" data-letter="T">T</span>
				  <span class="letter" data-letter="I">I</span>
				  <span class="letter" data-letter="O">O</span>
				  <span class="letter" data-letter="N">N</span>
	  
			</div>
		</div>
		<ul class="menu cf">
			<li><a href="/succesgeneration/">Home</a></li>
			<li><a href="/succesgeneration/cliente/mostrartrabalhos">Nossos
					Trabalhos</a></li>
			<li><a href="">Sobre a Empresa</a></li>
			<security:authorize access="!isAuthenticated()">
				<li><a href="/succesgeneration/login">Login</a>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<li><a href="/succesgeneration/admin/">Admin</a>
					<ul class="submenu">
						<li><a href="/succesgeneration/servicos/form">Adicionar
								Serviço</a></li>
						<li><a href="/succesgeneration/servicos/">Serviços</a></li>
						<li><a href="/succesgeneration/logout">Logout</a></li>
					</ul></li>
			</security:authorize>
		</ul>
		
		<div class="contato-header">
			<p><i class="fa fa-whatsapp" style="font-size:20px"> +1 55 227837</i></p>
			<p><i class="fa fa-envelope"> successgeneration@successgeneration.com</i></p>
		</div>
	</div>
	
	
	
	