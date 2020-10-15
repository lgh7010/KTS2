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
          <th>테스트 이름</th>
          <th>테스트 설명</th>
        </tr>
        </thead>
        <tbody>
          <tr>
            <td><input id="testName" type="text"></td>
            <td><input id="testDescription" type="text"></td>
          </tr>
        </tbody>
      </table>

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
        <tr v-for="(rel, index) in this.testRelTestcaseDerived">
          <td>{{index}}</td>
          <td>{{rel.name}}</td>
          <td>{{rel.description}}</td>
          <td>
            <button v-on:click="moveUp(index)">위로</button>
            <button v-on:click="moveDown(index)">아래로</button>
            <router-link :to="{name: 'TestcaseEdit', params: {testcaseGuid: rel.testcaseGuid}}"><button>편집</button></router-link>
            <button v-on:click="removeTestcase(index)">제거</button>
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
      testRelTestcaseDerived: [],
      testcaseList: [],
      currentTest: null,
      removeRelationSeqList: [],
    }
  },
  mounted: function() {
    axios.get("/test", { params: {'testSeq': this.$route.params.testSeq}}).then(responseTest => {
      this.currentTest = (responseTest.data.context != null && responseTest.data.context.test != null) ? responseTest.data.context.test : {
        testSeq: 0,
        name: "",
        description: "",
      }
      document.getElementById("testName").value = this.currentTest.name
      document.getElementById("testDescription").value = this.currentTest.description


      axios.get("/testRelTestcaseDerived", { params: {'testSeq': this.currentTest.testSeq}}).then(response => {
        this.testRelTestcaseDerived = response.data.context.list
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
      this.testRelTestcaseDerived.push({
        relationSeq: 0,
        testSeq : (this.currentTest != null) ? this.currentTest.testSeq : 0,
        testcaseGuid : testcase.testcaseGuid,
        name : testcase.name,
        description : testcase.description,
      })
    },
    onClickSave: function(){
      axios.post("/testRelTestcaseSave", {
        "relationList": this.testRelTestcaseDerived,
        "testSeq": (this.currentTest != null) ? this.currentTest.testSeq : 0,
        "testName": document.getElementById("testName").value,
        "testDescription": document.getElementById("testDescription").value,
        "removeRelationSeqList": this.removeRelationSeqList,
      }).then(response => {
        alert("저장 완료")
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    moveUp: function(listIndex){
      if(listIndex < 1){
        return
      }
      this.testRelTestcaseDerived[listIndex] = this.testRelTestcaseDerived.splice(listIndex - 1, 1, this.testRelTestcaseDerived[listIndex])[0]
    },
    moveDown: function(listIndex){
      if(listIndex > this.testRelTestcaseDerived.length - 2){
        return
      }
      this.testRelTestcaseDerived[listIndex] = this.testRelTestcaseDerived.splice(listIndex + 1, 1, this.testRelTestcaseDerived[listIndex])[0]
    },
    removeTestcase: function(listIndex){
      this.removeRelationSeqList.push(this.testRelTestcaseDerived[listIndex].relationSeq)
      this.testRelTestcaseDerived.splice(listIndex, 1)
      console.log(this.removeRelationSeqList)
    }
  }
}
</script>

<style scoped>

</style>