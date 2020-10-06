<template>
  <table>
    <thead>

    </thead>
    <tbody id="testList">
      <tr v-for="test in this.testList">
        <td>{{test.test_SEQ}}</td>
        <td>{{test.name}}</td>
        <td>{{test.description}}</td>
      </tr>

    </tbody>
  </table>
</template>

<script>
import axios from 'axios'

export default {
  name: 'testlistComponent',
  data: function(){
    return {
      testList: []
    }
  },
  created: function(){

  },
  mounted: function(){
    axios.get('/testListOnly').then(testList => {
      console.log(testList.data)
      this.testList = testList.data
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>