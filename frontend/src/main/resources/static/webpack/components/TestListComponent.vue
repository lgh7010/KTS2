<template>
  <div>
    <table>

      <th>
        <ul class="navbar-nav bg-gray-600 sidebar sidebar-dark accordion">
          <router-link :to="{name: 'TestEdit', params: {testGuid: 'DUMMY'}}"><button class="btn btn-primary">추가</button></router-link>
        </ul>
      </th>

      <th>
        <div class="d-flex flex-column">
          <div class="container-fluid">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>순서</th>
                <th>테스트 이름</th>
                <th>설명</th>
                <th>상호작용</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(test, index) in this.testList">
                <td>{{index + 1}}</td>
                <td>{{test.name}}</td>
                <td>{{test.description}}</td>
                <td>
                  <router-link :to="{name: 'TestEdit', params: { testGuid: test.testGuid }}"><button class="btn btn-info">편집</button></router-link>
                  <button class="btn btn-danger" v-on:click="onClickRemove(test.testGuid)">삭제</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
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
  name: 'testListComponent',
  data: function(){
    return {
      testList: []
    }
  },
  mounted: function(){
    axios.get('/findAllTest').then(response => {
      this.testList = response.data.context.list
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickRemove: function(testGuid){
      axios.post("/removeTest", {
        'testGuid': testGuid
      }).then(response => {
        alert((response.data.return_code == 0) ? '삭제 완료' : '삭제 실패')
        location.reload()
      }).catch(error => {
        alert(error)
      })
    }
  }
}
</script>

<style scoped>

</style>