import Vue from 'vue'
import Router from 'vue-router'

import TestEditComponent from "../components/TestEditComponent.vue"
import TestListComponent from "../components/TestListComponent.vue"
import TestcaseListComponent from "../components/TestcaseListComponent.vue";

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
        },
        {
            path: '/testcase/list',
            name: 'TestcaseList',
            component: TestcaseListComponent
        }
    ]
})

export default router