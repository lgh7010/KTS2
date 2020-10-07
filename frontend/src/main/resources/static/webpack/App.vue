<template>
  <div id="app2">
    <header-component/>
    <sidebar-component/>

    <test-list-component/>
    <test-edit-component/>
  </div>
</template>

<script>
import testListComponent from './components/TestListComponent.vue'
import testEditComponent from './components/TestEditComponent.vue'
import SidebarComponent from "./components/SidebarComponent.vue"
import HeaderComponent from "./components/HeaderComponent.vue"

export default {
  name: 'App',
  components: {
    HeaderComponent,
    SidebarComponent,
    testListComponent,
    testEditComponent
  }
}
</script>