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

    <div class="container" id="addTestcaseToTestLayer"
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
      <table class="table table-hover">
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
          <td><button class="btn btn-primary" v-on:click="onClickTestcaseAddToTest(testcase)">추가</button></td>
        </tr>
        </tbody>
      </table>
    </div>

    <table>

      <th>
        <ul class="navbar-nav bg-gray-600 sidebar sidebar-dark accordion">
          <table class="table table-hover">
            <tr>테스트 이름</tr>
            <tr><input id="testName" type="text"></tr>
            <tr>테스트 설명</tr>
            <tr><input id="testDescription" type="text"></tr>
            <tr>
              <button class="btn btn-primary" v-on:click="onClickAdd">추가</button>
              <button class="btn btn-success" v-on:click="onClickSave">저장</button>
              <router-link :to="{name: 'TestList'}"><button class="btn btn-secondary">닫기</button></router-link>
            </tr>
          </table>
        </ul>
      </th>

      <th>
        <table class="table table-hover">
          <thead>
          <tr>
            <th>순서</th>
            <th>테스트케이스 이름</th>
            <th>설명</th>
            <th>상호작용</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(rel, index) in this.testRelTestcaseJoinTestcase">
            <td>{{index}}</td>
            <td>{{rel.name}}</td>
            <td>{{rel.description}}</td>
            <td>
              <button class="btn btn-secondary" v-on:click="moveUp(index)">위로</button>
              <button class="btn btn-secondary" v-on:click="moveDown(index)">아래로</button>
              <router-link :to="{name: 'TestcaseEdit', params: {testcaseGuid: rel.testcaseGuid}}"><button class="btn btn-info">편집</button></router-link>
              <button class="btn btn-danger" v-on:click="removeTestcase(index)">제거</button>
            </td>
          </tr>
          </tbody>
        </table>
      </th>

    </table>

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
      testRelTestcaseJoinTestcase: [],
      testcaseList: [],
      currentTest: null,
    }
  },
  mounted: function() {
    axios.get("/findTest", { params: {'testGuid': this.$route.params.testGuid}}).then(responseTest => {
      this.currentTest = (responseTest.data.context != null && responseTest.data.context.test != null) ? responseTest.data.context.test : {
        testGuid: this.guid(),
        name: "",
        description: "",
      }
      document.getElementById("testName").value = this.currentTest.name
      document.getElementById("testDescription").value = this.currentTest.description


      axios.get("/testRelTestcaseJoinTestcase", { params: {'testGuid': this.currentTest.testGuid}}).then(response => {
        this.testRelTestcaseJoinTestcase = response.data.context.list
        console.log(this.testRelTestcaseJoinTestcase)
      }).catch(error => {
        console.log(error)
      })
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    guid(){
      function _p8(s) {
        var p = (Math.random().toString(16)+"000000000").substr(2,8);
        return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
      }
      return _p8() + _p8(true) + _p8(true) + _p8();
    },

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
      this.testRelTestcaseJoinTestcase.push({
        relationGuid: this.guid(),
        testGuid : this.currentTest.testGuid,
        testcaseGuid : testcase.testcaseGuid,
        name : testcase.name,
        description : testcase.description,
      })
    },
    onClickSave: function(){
      axios.post("/testRelTestcaseSave", {
        "relationList": this.testRelTestcaseJoinTestcase,
        "testGuid": this.currentTest.testGuid,
        "testName": document.getElementById("testName").value,
        "testDescription": document.getElementById("testDescription").value,
      }).then(response => {
        alert((response.return_code == 0) ? "저장 완료" : "저장 실패")
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    moveUp: function(listIndex){
      if(listIndex < 1){
        return
      }
      this.testRelTestcaseJoinTestcase[listIndex] = this.testRelTestcaseJoinTestcase.splice(listIndex - 1, 1, this.testRelTestcaseJoinTestcase[listIndex])[0]
    },
    moveDown: function(listIndex){
      if(listIndex > this.testRelTestcaseJoinTestcase.length - 2){
        return
      }
      this.testRelTestcaseJoinTestcase[listIndex] = this.testRelTestcaseJoinTestcase.splice(listIndex + 1, 1, this.testRelTestcaseJoinTestcase[listIndex])[0]
    },
    removeTestcase: function(listIndex){
      this.testRelTestcaseJoinTestcase.splice(listIndex, 1)
    }
  }
}
</script>

<style scoped>

</style>