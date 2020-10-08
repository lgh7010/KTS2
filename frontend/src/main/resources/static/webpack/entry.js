import Vue from 'vue'
import App from './App.vue'

import router from './router'

import HeaderComponent from "./components/layout/HeaderComponent.vue"
import SidebarComponent from "./components/layout/SidebarComponent.vue"

Vue.component('headerComponent', HeaderComponent)
Vue.component('sidebarComponent', SidebarComponent)

new Vue({
    el: '#app',
    router,
    render: h => h(App),
    components: { App },
    template: '<App/>'
})