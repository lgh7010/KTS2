import Vue from 'vue'
import axios from 'axios'
import App from './App.vue'

import testlistComponent from "./components/TestlistComponent.vue"

Vue.component('testlistComponent', testlistComponent)

Vue.prototype.testList = [
    {
        'test_SEQ': 1,
        'name': 'test1',
        'description': 'test1desc'
    },
    {
        'test_SEQ': 2,
        'name': 'test2',
        'description': 'test2desc'
    },
    {
        'test_SEQ': 3,
        'name': 'test3',
        'description': 'test3desc'
    }
]

new Vue({
    el: '#app',
    render: h => h(App),
    components: { App },
    template: '<App/>'
})

