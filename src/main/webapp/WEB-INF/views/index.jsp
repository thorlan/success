<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

<div class="panels">
		<div class="panel panel1">
			<p>Honestidade</p>
			<p></p>
			<p>Competência</p>
		</div>
		<div class="panel panel2 ">
			<p>Trabalhos</p>
			<p></p>
			<p>Com Drywall</p>	
		</div>
		<div class="panel panel3">
			<p>Success</p>
			<p></p>
			<p>Generation</p>
		</div>
		<div class="panel panel4">
			<p>Especialização</p>
			<p></p>
			<p>Em pinturas</p>
		</div>
		<div class="panel panel5">
			<p>Profissionalismo</p>
			<p></p>
			<p>Qualidade</p>
		</div>
	</div>


<div class="main">
	
	<div class="card card-1">
		<div class= "slogan">
				<p class="primeiralinha">Sinta-se a vontade para olhar os <a href="/succesgeneration/cliente/mostrartrabalhos">nossos
					trabalhos</a>.</p>
				<p class="segundalinha">Gostou? Entre em contato e solicite um <a>orçamento</a>.
				<p class="terceiralinha">Será um prazer poder te atender.</p>
			</div>
	</div>
	
</div>



<script async="" src="//www.google-analytics.com/analytics.js"></script>
<script>

	const panels = document.querySelectorAll('.panel');
	
	function toggleOpen() {
	  this.classList.toggle('open');
	  // console.log('abro/cierro')
	}
	
	 function toggleActive(e) {
	    //console.log(e)
	
	  if(e.propertyName.includes('flex')) {
	    this.classList.toggle('open-active')
	    }
	 } 
	  
	  panels.forEach(function(panel) {
	  panel.addEventListener('click', toggleOpen)
	  panel.addEventListener('transitionend', toggleActive)
	});

</script>
<script>

	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	ga('create', 'UA-46156385-1', 'cssscript.com');
	ga('send', 'pageview');

</script>

<%@ include file="/WEB-INF/views/rodape.jsp"%>