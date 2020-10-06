import Vue from 'vue'
import axios from 'axios'

new Vue({
    el: '#app',
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