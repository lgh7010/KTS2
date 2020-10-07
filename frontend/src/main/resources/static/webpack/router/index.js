import Vue from 'vue'
import Router from 'vue-router'

import TestEditComponent from "../components/TestEditComponent.vue"
import TestListComponent from "../components/TestListComponent.vue"

Vue.use(Router)

const router = new Router({
    routes: [
        {
            path: '/test/list',
            name: 'TestList',
            component: TestListComponent
        },
        {
            path: '/test/edit/:TEST_SEQ',
            name: 'TestEdit',
            component: TestEditComponent
        }
    ]
})

export default router