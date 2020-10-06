<template>
  <table>
    <thead>
      <tr>
        <th>순서</th>
        <th>테스트케이스 이름</th>
        <th>설명</th>
        <th>상호작용</th>
      </tr>
    </thead>
    <tbody id="testList">
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
  mounted: function(){
    axios.get('/testListOnly').then(testList => {
      this.testList = testList.data
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickEdit: function(TEST_SEQ){
      console.log(TEST_SEQ);
    },
    onClickRemove: function(TEST_SEQ){
      console.log(TEST_SEQ);
    }
  }
}
</script>