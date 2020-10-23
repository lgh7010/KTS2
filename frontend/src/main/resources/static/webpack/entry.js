import Vue from 'vue'
import App from './App.vue'

import router from './router'
import dataFilters from "./filters/dataFilters";

import HeaderComponent from "./components/layout/HeaderComponent.vue"
import SidebarComponent from "./components/layout/SidebarComponent.vue"

Vue.component('headerComponent', HeaderComponent)
Vue.component('sidebarComponent', SidebarComponent)

Vue.prototype.$filters = Vue.options.filters

Vue.use(dataFilters)

new Vue({
    el: '#app',
    router,
    render: h => h(App),
    components: { App },
    template: '<App/>'
})