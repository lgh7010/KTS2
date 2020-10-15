<template>
  <div>

    <div>
      <router-link :to="{name: 'TestcaseEdit', params: {TESTCASE_SEQ: 0}}"><button>추가</button></router-link>
    </div>

    <div>
      <table>
        <thead>
        <tr>
          <th>테스트케이스 이름</th>
          <th>설명</th>
          <th>상호작용</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="testcase in this.testcaseList">
          <td>{{testcase.name}}</td>
          <td>{{testcase.description}}</td>
          <td>
            <router-link :to="{name: 'TestcaseEdit', params: {TESTCASE_SEQ: testcase.testcase_SEQ}}"><button>편집</button></router-link>
            <button v-on:click="onClickRemove(testcase.testcase_SEQ)">삭제</button>
          </td>
        </tr>

        </tbody>
      </table>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import jQuery from 'jquery'
window.jQuery = window.$ = jQuery

export default {
  name: "TestcaseListComponent",
  data: function(){
    return {
      testcaseList: []
    }
  },
  mounted: function(){
    axios.get('/testcaseList').then(response => {
      this.testcaseList = response.data.context.testcaseList
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickRemove: function(TESTCASE_SEQ){
      axios.post('/removeTestcase', {'TESTCASE_SEQ': TESTCASE_SEQ}).then(resposne => {
        console.log(resposne)
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>