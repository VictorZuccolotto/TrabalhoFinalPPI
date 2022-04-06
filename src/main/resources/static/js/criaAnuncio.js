



const aproveita = {
	data() {
		return {
			anuncio: {
				nome: '',
				categoria: '',
				preco: '',
				imagem: false
			},
			erros: ''
		}
	},
	methods: {
		botao() {
			console.log(this.anuncio.imagem)
			axios
				.post('/anuncio/criarAnuncioDTO', this.anuncio, {})
				.then(() => {
					this.erros = ''
					document.querySelector("#app").submit();
					this.anuncio.nome = ''
					this.anuncio.categoria = ''
					this.anuncio.preco = ''
				}).catch(erro => {
					this.erros = erro.response.data
				})
		},
		imagemFunc(){
			this.anuncio.imagem = true
		}
	}
}
Vue.createApp(aproveita).mount('#app');
