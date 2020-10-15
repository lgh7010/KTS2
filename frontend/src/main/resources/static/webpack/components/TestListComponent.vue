<template>
  <div>
    <router-link :to="{name: 'TestEdit', params: {testSeq: 0}}"><button>추가</button></router-link>

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
        <tr v-for="(test, index) in this.testList">
          <td>{{index + 1}}</td>
          <td>{{test.name}}</td>
          <td>{{test.description}}</td>
          <td>
            <router-link :to="{name: 'TestEdit', params: { testSeq: test.testSeq }}"><button>편집</button></router-link>
            <button v-on:click="onClickRemove(test.testSeq)">삭제</button>
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
  name: 'testListComponent',
  data: function(){
    return {
      testList: []
    }
  },
  mounted: function(){
    axios.get('/testList').then(response => {
      this.testList = response.data.context.list
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickRemove: function(testSeq){
      axios.post("/removeTest", {
        'testSeq': testSeq
      }).then(response => {
        alert('삭제 완료')
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