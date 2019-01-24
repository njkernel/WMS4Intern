var model = new Vue({
        el: '#goodsList',
        data: {
            id: '',
            goodsList: []
        },
        methods: {
            countDown(index) {
                this.goodsList[index].num--
            },
            countUp(index) {
                this.goodsList[index].num++
            },
            getGoodsList(id) {

            }
        }
    })