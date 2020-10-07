<template>
  <div id="app2">
    <test-list-component/>
    <test-edit-component/>
  </div>
</template>

<script>
import testListComponent from './components/TestListComponent.vue'
import testEditComponent from './components/TestEditComponent.vue'

export default {
  name: 'App',
  components: {
    testListComponent,
    testEditComponent
  }
}
</script>