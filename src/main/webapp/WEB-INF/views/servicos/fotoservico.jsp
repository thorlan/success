<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

<div class="formularios">

	<div class="form-style-8 voando">
		<c:if test="${servico.id != null}">
			<h2>Alterar Servi�o</h2>
		</c:if>
		<c:if test="${servico.id == null}">
			<h2>Adicionar Servi�o</h2>
		</c:if>
		<form:form action="/succesgeneration/servicos/servicosAdd"
			method="POST" modelAttribute="servico">
			<form:input type="text" required="required" path="nome"
				placeholder="O que foi feito: Drywall/Reparo..." />
			<form:input type="text" path="inicio" placeholder="In�cio dd/mm/aaaa" />
			<form:input type="text" path="termino"
				placeholder="Termino dd/mm/aaaa" />
			<form:input type="text" path="endereco.pais" placeholder="Pa�s" />
			<form:input type="text" path="endereco.localidade"
				placeholder="Local" />
			<form:textarea class="form-control" placeholder="Descri��o"
				path="descricao" rows="10" cols="30"></form:textarea>
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


	<div class="form-style-8 voando">
		<h2>Inserir Foto</h2>
		<form:form
			servletRelativeAction="${s:mvcUrl('FC#addFoto').arg(0,foto)
						.arg(1,servico.id).arg(2,servico.nome)
						.build() }"
			modelAttribute="foto" method="post" enctype="multipart/form-data">

			<form:input required="required" type="file" class="form-control"
				id="caminho" path="caminho" aria-describedby="inicioHelp" />
			<form:input type="text" class="form-control" id="descricao"
				path="descricao" aria-describedby="inicioHelp"
				placeholder="Breve descri��o da imagem" />

			<input type="submit" value="Inserir" class="btn btn-primary" />
		</form:form>
	</div>
</div>

<div class="container">
	<br>

	<c:if test="${mensagem.startsWith('Foto')}">
		<div class="alert alert-success alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>${mensagem }</strong>
		</div>
	</c:if>
	<c:if test="${mensagem.startsWith('N�o')}">
		<div class="alert alert-danger alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>${mensagem }</strong> <br>
			<span>Voc� pode apagar o servico clicando 
				<a href="/succesgeneration/servicos/">aqui</a> e depois clique na lixeira
				referente ao servi�o desejado
			</span>
		</div>
	</c:if>

</div>

<div class="main">

	<c:forEach items="${fotos}" var="foto">
		<div class="fotos-edicao">
			<div class="gallery">

				<c:if test="${servico.fotoPrincipal.id != foto.id}">
					<a href="${s:mvcUrl('FC#tornarPrincipal').arg(0,foto.id).build() }">
						<div class="deletar-icone">
							<img src="${imgPath}/mainPageIcon.png"
								alt="Tornar foto principal"
								title="Mostrar como capa em 'nossos servi�os'" />
						</div>
					</a>
				</c:if>


				<img src="${foto.caminho}" alt="fotoServico">
				<div class="desc">${foto.descricao }</div>
				<a href="${s:mvcUrl('FC#excluirFoto').arg(0,foto.id).build() }">
					<div class="deletar-icone">
						<img src="${imgPath}/lixeira.png" title="Deletar foto" />
					</div>
				</a>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/views/rodape.jsp"%>