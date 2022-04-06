const aproveita = {
	data() {
		return {
			user: [],
			userDTO: []
		}
	},
	mounted() {
		axios
			.get('/user/getUserDTO')
			.then(response => {
				this.userDTO = response.data
			}
			)
//			.catch(() => {
//				this.mensagem = 'Voce não tem nenhum anuncio :/';
//			})
	},
	methods: {
		botao() {
//			console.log(this.userDTO)
			axios.put('user/atualizaUser/', this.userDTO, {})
				.then(() =>{
//					window.location.href = "/meus-anuncios";
				}).catch(() => {
					window.alert("ERRO")
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