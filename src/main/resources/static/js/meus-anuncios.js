const aproveita = {
	data() {
		return {
			anuncios: [],
			url: '/anuncio?id=',
			url2: '/imagem/',
			mensagem: 'Confira os anúncios'
		}
	},
	mounted() {
		axios
			.get('/user/meusAnuncios')
			.then(response => {
				this.anuncios = response.data
			}
			).catch(() => {
				this.mensagem = 'Voce não tem nenhum anuncio :/';
			})
	},
	methods: {
		anuncioLink(id) {
			return this.url + id
		},
		imagem(id) {
			return this.url2 + id
		},
		excluir(id) {
			axios.delete('user/excluir/' + id)
				.then(() =>{
					window.location.href = "/meus-anuncios";
				}).catch(() => {
					window.alert("Nao foi possivel exlcuir este anuncio")
				})
		},
		reloadPage() {
			axios
				.get('/user/meusAnuncios')
				.then(response => {
					this.anuncios = response.data
				}
				).catch(() => {
					this.mensagem = 'Voce não tem nenhum anuncio :/';
				})
		}
	}
}
Vue.createApp(aproveita).mount('#app');