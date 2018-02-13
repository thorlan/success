<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

<div class ="main">
	<div class="fotos-trabalhos">
		<c:forEach items="${servicos}" var="servico">
			<div
				style="background-image: url(${servico.fotoPrincipal.caminho })"; class="miniatura">
				<a href="${s:mvcUrl('SC#mostrarServico').arg(0,servico.id).build() }">
					<div class="blackbox">
						<div class="blackbox-text">
							<h1>${servico.nome }</h1>
							<h2>${servico.descricao }</h2>
						</div>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>

</div>


<%@ include file="/WEB-INF/views/rodape.jsp"%>