<template>
  <div>
    <table id="testList">
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

    <test-edit-component/>
  </div>
</template>

<script>
import axios from 'axios'
import jQuery from 'jquery'
window.jQuery = window.$ = jQuery

import {EventBus} from "./bus.js";
import TestEditComponent from "./TestEditComponent.vue";

export default {
  name: 'testListComponent',
  components: {TestEditComponent},
  created: function(){
    EventBus.$on('closeEditPage', () => {
      this.onClickCloseEditPage()
    })
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
      $("#testList").hide()
      EventBus.$emit('openEditPage', TEST_SEQ)
    },
    onClickRemove: function(TEST_SEQ){
      console.log("remove test : " + TEST_SEQ)
    },
    onClickCloseEditPage: function(){
      $("#testList").show()
    }
  }
}
</script>

<style scoped>

</style>