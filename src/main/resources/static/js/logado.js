

const aproveita2 = {
	data() {
		return {
			nome: '',
			bool: false

		}
	},
	methods: {
		logado() {
			axios
				.get('/user/getNome')
						.then(response => {
								this.nome = response.data;
								this.bool = true;
						})
				.catch(() => {
					this.bool = false;
				})
			return this.bool;
		}
	}
}
Vue.createApp(aproveita2).mount('#app2');