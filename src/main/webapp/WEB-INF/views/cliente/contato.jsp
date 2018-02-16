<%@ include file="/WEB-INF/views/cabecalho.jsp"%>
<div class="container">
	<br>
	<c:if test="${mensagem.length() > 2}">
		<div class="alert alert-success alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<p>${mensagem }</p>
		</div>
	</c:if>
</div>

<div class="formularios">

	<div class="form-style-8">
		<h2>Envie-nos uma mensagem</h2>
		<form:form action="/succesgeneration/enviaEmail" method="POST"
			modelAttribute="email">

			<form:label path="nome">Seu nome:</form:label>
			<form:input type="text" name="nome" path="nome" />

			<form:label path="email">Seu e-mail:</form:label>
			<form:input required="required" type="text" name="email" path="email" />

			<form:label path="telefone">Seu telefone:</form:label>
			<form:input type="text" name="telefone" path="telefone" />

			<form:label path="mensagem">Sua mensagem:</form:label>
			<form:textarea class="form-control" name="mensagem" path="mensagem"
				rows="10" cols="30" />

			<form:label path="estaNaLista">Desejo receber novidades via e-mail</form:label>
			<form:checkbox path="estaNaLista" name="estaNaLista" value="true" />
			<div class="form-group">
				<input type="submit" value="Enviar" class="btn btn-primary" />
			</div>

		</form:form>
	</div>
</div>

<!-- // End #container -->
<%@ include file="/WEB-INF/views/rodape.jsp"%>