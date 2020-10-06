<template>
  <div id="app2">
    <testlist-component/>
  </div>
</template>

<script>
import testlistComponent from './components/TestlistComponent.vue'

export default {
  name: 'App',
  components: {
    testlistComponent
  }
}
</script>