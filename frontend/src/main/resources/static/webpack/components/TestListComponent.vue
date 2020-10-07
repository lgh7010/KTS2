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
          <button v-on:click="onClickEdit(test.test_SEQ)">편집</button>
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
import TestEditComponent from "./TestEditComponent.vue";

import {EventBus} from "./bus.js";

export default {
  name: 'testListComponent',
  components: {
    TestEditComponent
  },
  data: function(){
    return {
      testList: []
    }
  },
  mounted: function(){
    axios.get('/testListOnly').then(testList => {
      this.testList = testList.data
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickEdit: function(TEST_SEQ){
      EventBus.$emit('openEditPage', TEST_SEQ)
      //TestEditComponent.methods.openEditPage(TEST_SEQ)//이렇게 하면 vue 리로드가 안되는듯.
    },
    onClickRemove: function(TEST_SEQ){
      console.log("remove test : " + TEST_SEQ)
    }
  }
}
</script>

<style scoped>

</style>