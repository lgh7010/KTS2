<template>
  <div>
    <table>
      <thead>
      <tr>
        <th>순서</th>
        <th>테스트 이름</th>
        <th>설명</th>
        <th>상호작용</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="test in this.testList">
        <td>{{test.test_SEQ}}</td>
        <td>{{test.name}}</td>
        <td>{{test.description}}</td>
        <td>
          <router-link :to="{name: 'TestEdit', params: {TEST_SEQ: test.test_SEQ}}"><button>편집</button></router-link>
          <button v-on:click="onClickRemove(test.test_SEQ)">삭제</button>
        </td>
      </tr>

      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
import jQuery from 'jquery'
window.jQuery = window.$ = jQuery

export default {
  name: 'testListComponent',
  data: function(){
    return {
      testList: []
    }
  },
  mounted: function(){
    axios.get('/testList').then(testList => {
      this.testList = testList.data
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickRemove: function(TEST_SEQ){
      console.log("remove test : " + TEST_SEQ)
    }
  }
}
</script>

<style scoped>

</style>