<template>
  <div>

    <div id="addTestcaseToTestLayerBackground"
         style="display: none;
        width: 100vw;
        height: 100vh;
        position: fixed;
        background-color:gray;
        opacity: 0.5;
        z-index:10;"/>

    <div id="addTestcaseToTestLayer"
         style="display: none;
        width: 500px;
        height: 500px;
        position: fixed;
        left: 50%;
        top: 50%;
        margin-left: -250px;
        margin-top: -250px;
        background-color: white;
        z-index:11;">
      <button v-on:click="onClickCloseTestcaseToTestLayer">닫기</button>
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
          <td><button v-on:click="onClickTestcaseAddToTest(testcase)">추가</button></td>
        </tr>
        </tbody>
      </table>
    </div>

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
          <td>{{rel.list_INDEX}}</td>
          <td>{{rel.name}}</td>
          <td>{{rel.description}}</td>
          <td>
            <button v-on:click="moveUp(rel.relation_SEQ)">위로</button>
            <button v-on:click="moveDown(rel.relation_SEQ)">아래로</button>
            <router-link :to="{name: 'TestcaseEdit', params: {TESTCASE_SEQ: rel.testcase_SEQ}}"><button>편집</button></router-link>
            <button v-on:click="removeTestcase(rel.relation_SEQ)">제거</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <hr>

    <button v-on:click="onClickAdd">추가</button>
    <button v-on:click="onClickSave">저장</button>
    <router-link :to="{name: 'TestList'}"><button>닫기</button></router-link>
  </div>
</template>

<script>
import jQuery from 'jquery'
import axios from "axios";
window.jQuery = window.$ = jQuery

export default {
  name: "testEditComponent",
  data: function(){
    return {
      currentTEST_SEQ: 0,
      testRelTestcaseList_with_name_and_desc: [],
      testcaseList: []
    }
  },
  mounted: function() {
    this.currentTEST_SEQ = this.$route.params.TEST_SEQ

    //쿼리 한번으로 해결하려면 TEST_REL_TESTCASE에 'NAME'과 'DESCRIPTION'을 더한 도메인 클래스를 따로 또 추가해야 해서 이런식으로 처리함.
    //편의성과 일관성 사이의 Tradeoff에서 결정한 사항.
    axios.get("/testRelTestcaseList", { params: {'TEST_SEQ': this.currentTEST_SEQ}}).then(response => {
      var testRelTestcaseList = response.data.context.testRelTestcaseList
      axios.get("/testcaseDic", { params: {'TEST_SEQ': this.currentTEST_SEQ}}).then(response2 => {
        var testcaseDic = response2.data.context.testcaseDic
        var list = []
        for(var i = 0; i < testRelTestcaseList.length; i++){
          list.push({
            relation_SEQ: testRelTestcaseList[i].relation_SEQ,
            test_SEQ : testRelTestcaseList[i].test_SEQ,
            list_INDEX : testRelTestcaseList[i].list_INDEX,
            testcase_SEQ : testRelTestcaseList[i].testcase_SEQ,
            name : testcaseDic[testRelTestcaseList[i].testcase_SEQ].name,
            description : testcaseDic[testRelTestcaseList[i].testcase_SEQ].description
          })
        }
        this.testRelTestcaseList_with_name_and_desc = list
      }).catch(error => {
        console.log(error)
      })
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    onClickAdd: function(){
      axios.get('/testcaseList').then(response => {
        this.testcaseList = response.data.context.testcaseList

        $("#addTestcaseToTestLayerBackground").show()
        $("#addTestcaseToTestLayer").show()
      }).catch(error => {
        console.log(error)
      })
    },
    onClickCloseTestcaseToTestLayer: function(){
      $("#addTestcaseToTestLayerBackground").hide()
      $("#addTestcaseToTestLayer").hide()
    },
    onClickTestcaseAddToTest: function(testcase){
      this.testRelTestcaseList_with_name_and_desc.push({
        relation_SEQ: 0,
        test_SEQ : this.currentTEST_SEQ,
        list_INDEX : this.testRelTestcaseList_with_name_and_desc.length,
        testcase_SEQ : testcase.testcase_SEQ,
        name : testcase.name,
        description : testcase.description
      })
    },
    onClickSave: function(){
      axios.post("/testRelTestcaseSave", {
        "REL_LIST": this.testRelTestcaseList_with_name_and_desc,
        "TEST_SEQ": this.currentTEST_SEQ
      }).then(response => {
        alert("저장 완료")
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    moveUp: function(RELATION_SEQ){
      console.log("moveUp : " + RELATION_SEQ)
    },
    moveDown: function(RELATION_SEQ){
      console.log("moveDown : " + RELATION_SEQ)
    },
    removeTestcase: function(RELATION_SEQ){
      console.log("remove : " + RELATION_SEQ)
    }
  }
}
</script>

<style scoped>

</style>