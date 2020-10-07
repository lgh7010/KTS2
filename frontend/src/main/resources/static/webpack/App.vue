<template>
  <div id="app2">
    <test-list-component/>
  </div>
</template>

<script>
import testListComponent from './components/TestListComponent.vue'

export default {
  name: 'App',
  components: {
    testListComponent
  }
}
</script>