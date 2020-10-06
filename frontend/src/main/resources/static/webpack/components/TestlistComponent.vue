<template>
  <table>
    <thead>

    </thead>
    <tbody id="testList">
      <tr v-for="test in testList">
        <td>{{test.test_SEQ}}</td>
        <td>{{test.name}}</td>
        <td>{{test.description}}</td>
      </tr>

    </tbody>
  </table>
</template>

<script>
import axios from 'axios'
import Vue from 'vue'

export default {
  name: 'testlistComponent',
  created: function(){

  },
  mounted: function(){
    axios.get('/testListOnly').then(testList => {
      console.log(testList.data)
      Vue.prototype.testList = testList.data
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>