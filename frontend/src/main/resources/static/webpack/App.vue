<template>
  <div id="app2">
    <header-component/>
    <table>
      <th>
        <sidebar-component/>
      </th>
      <th>
        <router-view/>
      </th>
    </table>
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