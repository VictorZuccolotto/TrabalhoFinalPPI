const aproveita = {
	data() {
		return {
			produtos: [],
			url: '/anuncio?id=',
			url2: '/imagem/'
		}
	},
	mounted() {
		axios
			.get('/anuncio/anunciosRecentes')
			.then(response => {
				this.produtos = response.data
			}
			)
	},
	methods: {
		anuncio(id) {
			return this.url + id
		},
		imagem(id) {
			return this.url2 + id
		}
	}
}
Vue.createApp(aproveita).mount('#app');

const aproveita4 = {
	data() {
		return {
		}
	},
	methods: {
		sair() {
			document.cookie = "token" +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		}
	}
}
Vue.createApp(aproveita4).mount('#app4');


const aproveita3 = {
	data() {
		return {
			nome: '',
		}
	},
	methods: {
		pesquisar() {
			window.location.href = "/categoria?nome="+ this.nome;
		}
	}
}
Vue.createApp(aproveita3).mount('#app3');

