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
<div class="main">
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th scope="col">Serviço</th>
				<th scope="col">Inicio</th>
				<th scope="col">Termino</th>
				<th scope="col">Pais</th>
				<th scope="col">Localidade</th>
				<th scope="col">-</th>
				<th scope="col">-</th>
				<th scope="col">-</th>
				<th scope="col">-</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${servicos}" var="servico">
				<tr>
					<th scope="row"><a
						href="${s:mvcUrl('SC#mostrarServico').arg(0,servico.id).build() }">
							${servico.nome } </a></th>
					<td>${servico.inicio }</td>
					<td>${servico.termino }</td>
					<td>${servico.endereco.pais }</td>
					<td>${servico.endereco.localidade }</td>
					<td><a
						href="${s:mvcUrl('SC#excluir').arg(0,servico.id).build() }"
						onclick="return confirm('Tem certeza que deseja excluir o serviço?');">
							<img src="${imgPath}/lixeira.png" alt="" width="25" height="25"
							data-id="${servico.id }" />
					</a></td>
					<td><a
						href="${s:mvcUrl('FC#mostrarComFotos').arg(0,servico.id).build() }">
							<img src="${imgPath}/iPhoto.png" alt="" width="25" height="25" />
					</a></td>
					<td><a
						href="${s:mvcUrl('SC#enviarPorEmail').arg(0,servico.id).build() }">
							<img src="${imgPath}/email.png" alt="" width="25" height="25" />
					</a></td>
					<td><a href="#"> FB </a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>
<%@ include file="/WEB-INF/views/rodape.jsp"%>