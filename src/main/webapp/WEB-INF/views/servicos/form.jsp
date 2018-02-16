<%@ include file="/WEB-INF/views/cabecalho.jsp"%>
<div class ="formularios">
	<div class="form-style-8">
		<c:if test="${servico.id != null}">
			<h2>Alterar Servi�o</h2>
		</c:if>
		<c:if test="${servico.id == null}">
			<h2>Adicionar Servi�o</h2>
		</c:if>
		<form:form action="/succesgeneration/servicos/servicosAdd"
			method="POST" modelAttribute="servico">
			<form:input type="text" required="required" path="nome" placeholder="O que foi feito: Drywall/Reparo..." />
			<form:input type="text" path="inicio" placeholder="In�cio dd/mm/aaaa" />
			<form:input type="text" path="termino" placeholder="Termino dd/mm/aaaa" />
			<form:input type="text" path="endereco.pais" placeholder="Pa�s" />
			<form:input type="text" path="endereco.localidade" placeholder="Local" />
			<form:textarea class="form-control" path="descricao" placeholder="Descri��o" rows="10" cols="30" />
				
			<c:if test="${servico.id != null}">
				<div class="form-group">
					<input type="submit" value="Atualizar" class="btn btn-primary" />
					<input type="hidden" name="id" value="${servico.id}">
				</div>
			</c:if>
			<c:if test="${servico.id == null}">
				<div class="form-group">
					<input type="submit" value="Cadastrar" class="btn btn-primary" />
				</div>
			</c:if>
		</form:form>
	</div>
</div>
	
<%@ include file="/WEB-INF/views/rodape.jsp"%>
