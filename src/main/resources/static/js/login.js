const aproveita = {
	data() {
		return {
			login: {
				email: '',
				senha: ''
			},
			erros: ''
		}
	},
	//    mounted() {

	//    },
	methods: {
		botao() {
			axios
				.post('/login', this.login, {})
				.then(() => {
					window.location.href = "/";
				}
				).catch(() => {
					this.erros = 'Email ou senha incorretos';
				})
		}
	}
}
Vue.createApp(aproveita).mount('#app');
