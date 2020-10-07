<template>
  <div id="app2">
    <header-component/>
    <sidebar-component/>

    <router-view/>
  </div>
</template>

<script>
import SidebarComponent from "./components/layout/SidebarComponent.vue"
import HeaderComponent from "./components/layout/HeaderComponent.vue"

export default {
  name: 'App',
  components: {
    HeaderComponent,
    SidebarComponent
  }
}
</script>