<%@ include file="/WEB-INF/views/cabecalho.jsp"%>
<div class="container">
	<security:authorize access="!isAuthenticated()">
		
			<h1>Login Success Generation</h1>
			<form:form servletRelativeAction="/login" method="post">
				<div class="form-group">
					<label>Nome</label> <input type="text" name="username"
						class="form-control" />
				</div>
				<div class="form-group">
					<label>Senha</label> <input type="password" name="password"
						class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">Logar</button>
			</form:form>
		
	</security:authorize>
	
	<security:authorize access="isAuthenticated()">
		<h1>Seja bem vindo, clique <a href="/succesgeneration/admin/">aqui</a> para ir a página do administrador</h1>
	</security:authorize>
</div>
	
<%@ include file="/WEB-INF/views/rodape.jsp"%>