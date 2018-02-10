<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<div class="main">

	<p>${mensagem }</p>

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
					<td>
						<a href="#confirm" data-toggle="modal"
							data-target="#confirm"> <img src="${imgPath}/lixeira.png"
								alt="" width="25" height="25" />
						</a>
						<div class="modal fade" id="confirm" role="dialog">
							<div class="modal-dialog modal-md">

								<div class="modal-content">
									<div class="modal-body">
										<p>Deseja realmente excluir esse serviço com todas as
											fotos?</p>
									</div>
									<div class="modal-footer">
										<a href="${s:mvcUrl('SC#excluir').arg(0,servico.id).build() }"
											type="button" class="btn btn-danger" id="delete">Apagar
											Registo</a>
										<button type="button" data-dismiss="modal"
											class="btn btn-default">Cancelar</button>
									</div>
								</div>

							</div>
						</div></td>
					<td><a
						href="${s:mvcUrl('FC#mostrarComFotos').arg(0,servico.id).build() }">
							<img src="${imgPath}/iPhoto.png" alt="" width="25" height="25" />
					</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>
<%@ include file="/WEB-INF/views/rodape.jsp"%>