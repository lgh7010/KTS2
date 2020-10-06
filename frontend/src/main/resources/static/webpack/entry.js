import Vue from 'vue'
import axios from 'axios'
import App from './App.vue'

new Vue({
    el: '#app',
    render: h => h(App),
    components: { App },
    template: '<App/>',
    created: function(){
        console.log("vue is created");
        this.drawTestList()
    },
    methods: {
        drawTestList: function(){
            axios.get('/testListOnly').then(testList => {
                console.log(testList);
            })
        }
    }
})