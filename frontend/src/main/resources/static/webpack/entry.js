import Vue from 'vue'
import axios from 'axios'
import App from './App.vue'

import testlistComponent from "./components/TestlistComponent.vue"

Vue.component('testlistComponent', testlistComponent)

new Vue({
    el: '#app',
    render: h => h(App),
    components: { App },
    template: '<App/>'
})

