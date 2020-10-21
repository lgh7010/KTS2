<template>
  <div>

    <table>

      <th>
        <ul class="navbar-nav bg-gray-600 sidebar sidebar-dark accordion">
          <router-link :to="{name: 'TestcaseEdit', params: {testcaseGuid: 'DUMMY'}}"><button class="btn btn-primary">추가</button></router-link>
        </ul>
      </th>

      <th>
        <div>
          <table class="table">
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
                <router-link :to="{name: 'TestcaseEdit', params: {testcaseGuid: testcase.testcaseGuid}}"><button class="btn btn-info">편집</button></router-link>
                <button class="btn btn-danger" v-on:click="onClickRemove(testcase.testcaseGuid)">삭제</button>
              </td>
            </tr>

            </tbody>
          </table>
        </div>
      </th>
    </table>

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
    onClickRemove: function(testcaseGuid){
      axios.post('/removeTestcase', {'testcaseGuid': testcaseGuid}).then(response => {
        console.log(resposne)
        if(response.return_code == 0){
          location.reload()
        }
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>