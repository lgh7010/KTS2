<template>
  <div>
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
            <button v-on:click="moveUp(rel.RELATION_SEQ)">위로</button>
            <button v-on:click="moveDown(rel.RELATION_SEQ)">아래로</button>
            <router-link :to="{name: 'TestcaseEdit', params: {TESTCASE_SEQ: rel.TESTCASE_SEQ}}"><button>편집</button></router-link>
            <button v-on:click="removeTestcase(rel.RELATION_SEQ)">제거</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <hr>
    <button>추가</button>
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
      testRelTestcaseList_with_name_and_desc: []
    }
  },
  mounted: function() {
    var TEST_SEQ = this.$route.params.TEST_SEQ

    //쿼리 한번으로 해결하려면 TEST_REL_TESTCASE에 'NAME'과 'DESCRIPTION'을 더한 도메인 클래스를 따로 또 추가해야 해서 이런식으로 처리함.
    //편의성과 일관성 사이의 Tradeoff에서 결정한 사항.
    axios.get("/testRelTestcaseList", { params: {'TEST_SEQ': TEST_SEQ}}).then(response => {
      var testRelTestcaseList = response.data.context.testRelTestcaseList
      axios.get("/testcaseDic", { params: {'TEST_SEQ': TEST_SEQ}}).then(response2 => {
        var testcaseDic = response2.data.context.testcaseDic
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
      }).catch(error => {
        console.log(error)
      })
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
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