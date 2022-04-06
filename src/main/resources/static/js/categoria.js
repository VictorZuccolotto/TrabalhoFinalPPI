const aproveita = {
	data() {
		return {
			anuncios: [],
			url: '/anuncio?id=',
			url2: '/imagem/',
			nome: window.location.href.split('nome=')[1],
			mensagem: 'Confira os anúncios'
		}
	},
	mounted() {
		axios
			.get('/anuncio/cat/' + this.nome)
			.then(response => {
				this.anuncios = response.data
			}
			).catch(() => {
				axios.get('/anuncio/nome/' + this.nome)
					.then(resp => {
						this.anuncios = resp.data
					}).catch(() => {
						this.mensagem = 'Nada foi encontrado :/';
					})
			})
	},
	methods: {
		anuncioLink(id) {
			return this.url + id
		},
		imagem(id) {
			return this.url2 + id
		}
	}
}
Vue.createApp(aproveita).mount('#app');