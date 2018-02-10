<%@ include file="/WEB-INF/views/cabecalho.jsp"%>
<div class ="formularios">
	<div class="form-style-8">
		<c:if test="${servico.id != null}">
			<h2>Alterar Serviço</h2>
		</c:if>
		<c:if test="${servico.id == null}">
			<h2>Adicionar Serviço</h2>
		</c:if>
		<form:form action="/succesgeneration/servicos/servicosAdd"
			method="POST" modelAttribute="servico">
			<form:input type="text" path="nome" placeholder="O que foi feito: Drywall/Reparo..." />
			<form:input type="text" path="inicio" placeholder="Início dd/mm/aaaa" />
			<form:input type="text" path="termino" placeholder="Termino dd/mm/aaaa" />
			<form:input type="text" path="endereco.pais" placeholder="País" />
			<form:input type="text" path="endereco.localidade" placeholder="Local" />
			
			<form:input type="text" path="descricao" placeholder="Descrição" />
				
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
