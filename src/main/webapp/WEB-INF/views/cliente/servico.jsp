<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

	<div class="main">
			
			
			
				<h1>${servico.nome }</h1>
				<div class="descricao-servico"> ${servico.descricao }</div>
				<h2>${servico.endereco.pais } / ${servico.endereco.localidade }</h2>
				<h2>${servico.inicio } - ${servico.termino }</h2>
			
		
		<c:forEach items="${servico.fotos}" var="foto">
			<img class="foto-trabalhos" src="${foto.caminho }"/>
			<figcaption class="foto-descricao">${foto.descricao}</figcaption>
		</c:forEach>

	</div>
<%@ include file="/WEB-INF/views/rodape.jsp"%>