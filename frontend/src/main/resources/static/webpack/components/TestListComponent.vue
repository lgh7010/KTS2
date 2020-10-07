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

    <!--layers-->
    <div id="testEditLayer" class="testEditLayer" style="display:none">
      <div>
        <table>
          <thead>
          <tr>
            <th>순서</th>
            <th>테스트케이스 이름</th>
            <th>설명</th>
            <th>상호작용</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="rel in this.testRelTestcaseList_with_name_and_desc">
            <td>{{rel.LIST_INDEX}}</td>
            <td>{{rel.NAME}}</td>
            <td>{{rel.DESCRIPTION}}</td>
            <td>
              <button>위로</button>
              <button>아래로</button>
              <button>편집</button>
              <button>제거</button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <hr>
      <button>추가</button>
      <button v-on:click="onCloseEdit()">닫기</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ajax from 'ajax'
import jQuery from 'jquery'
window.jQuery = window.$ = jQuery

export default {
  name: 'testListComponent',
  data: function(){
    return {
      testList: [],
      testRelTestcaseList_with_name_and_desc: []
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
      //쿼리 한번으로 해결하려면 '관계'에 대한 도메인 클래스를 추가해야 해서 이런식으로 처리함. 근데 도메인 추가하는게 나은듯.
      ajax.post("/testRelTestcaseList", {TEST_SEQ: TEST_SEQ}, testRelTestcaseList => {
        ajax.post("/testcaseDic", {TEST_SEQ: TEST_SEQ}, testcaseDic => {
          var list = []
          for(var i = 0; i < testRelTestcaseList.length; i++){
            list.push({
              RELATION_SEQ: testRelTestcaseList[i].relation_SEQ,
              TEST_SEQ : testRelTestcaseList[i].test_SEQ,
              LIST_INDEX : testRelTestcaseList[i].list_INDEX,
              TESTCASE_SEQ : testRelTestcaseList[i].testcase_SEQ,
              NAME : testcaseDic[testRelTestcaseList[i].testcase_SEQ].name,
              DESCRIPTION : testcaseDic[testRelTestcaseList[i].testcase_SEQ].description
            })
          }
          this.testRelTestcaseList_with_name_and_desc = list
          $("#testEditLayer").show();
        })
      })
      // axios.post('/testRelTestcaseList', {TEST_SEQ: TEST_SEQ}).then(testRelTestcaseList => {
      //   axios.post('/testcaseDic', {TEST_SEQ: TEST_SEQ}).then(testcaseDic => {
      //     var list = []
      //     for(var i = 0; i < testRelTestcaseList.length; i++){
      //       list.push({
      //         RELATION_SEQ: testRelTestcaseList[i].relation_SEQ,
      //         TEST_SEQ : testRelTestcaseList[i].test_SEQ,
      //         LIST_INDEX : testRelTestcaseList[i].list_INDEX,
      //         TESTCASE_SEQ : testRelTestcaseList[i].testcase_SEQ,
      //         NAME : testcaseDic[testRelTestcaseList[i].testcase_SEQ].name,
      //         DESCRIPTION : testcaseDic[testRelTestcaseList[i].testcase_SEQ].description
      //       })
      //     }
      //     this.testRelTestcaseList_with_name_and_desc = list
      //     console.log(list)
      //   }).catch(error => {
      //     console.log(error)
      //   })
      // }).catch(error => {
      //   console.log(error)
      // })
    },
    onClickRemove: function(TEST_SEQ){
      console.log(TEST_SEQ)
    },
    onCloseEdit: function(){
      $("#testEditLayer").hide();
    }
  }
}
</script>

<style scoped>
  .testEditLayer {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0%;
    top: 0%;
    border:2px solid #ccc;
    overflow:auto;
    background-color:white;
    border-radius: 5px;
  }
</style>