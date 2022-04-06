const anuncio = {
	data() {
		return {
			anuncio: [],
			url: '/imagem/0',
			id: window.location.href.split('id=')[1],
		}
	},
	mounted() {
		axios
			.get('/anuncio/info/'+this.id)
			.then(response => {
						this.anuncio = response.data
						this.url += this.anuncio.id

			})
	},
	methods: {
		whatsapp(){
			 return "https://api.whatsapp.com/send?phone=+55" + this.anuncio.telefone + "&text=Gostaria+de+saber+mais+sobre+"+ this.anuncio.nome +" anunciado na Aproveita"
		}
	}
}
Vue.createApp(anuncio).mount('#anuncio');