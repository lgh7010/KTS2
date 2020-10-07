import Vue from 'vue'
import App from './App.vue'

import testListComponent from "./components/TestListComponent.vue"
import testEditComponent from "./components/TestEditComponent.vue"

Vue.component('testListComponent', testListComponent)
Vue.component('testEditComponent', testEditComponent)

new Vue({
    el: '#app',
    render: h => h(App),
    components: { App },
    template: '<App/>'
})