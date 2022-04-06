const aproveita = {
	data() {
		return {
			user: {
				nome: '',
				sobrenome: '',
				email: '',
				senha: '',
				telefone: '',
				cep: ''
			},
			erros: ''
		}
	},
	//    mounted() {

	//    },
	methods: {
		botao() {
			axios
				.post('/cadastro', this.user, {})
				.then(resp => {
					window.location.href = "/";
				}
				).catch(error =>{
//					console.log(error.response.data)
					this.erros = error.response.data;
				})
		}
	}
}
Vue.createApp(aproveita).mount('#app');
