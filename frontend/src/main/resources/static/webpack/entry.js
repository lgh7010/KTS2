import Vue from 'vue'
import App from './App.vue'

import testListComponent from "./components/TestListComponent.vue"

Vue.component('testListComponent', testListComponent)

new Vue({
    el: '#app',
    render: h => h(App),
    components: { App },
    template: '<App/>'
})