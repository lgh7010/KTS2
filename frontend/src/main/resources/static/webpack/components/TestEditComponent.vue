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
    <router-link :to="{name: 'TestList'}"><button>닫기</button></router-link>
  </div>
</template>

<script>
import ajax from "ajax";
import jQuery from 'jquery'
window.jQuery = window.$ = jQuery

export default {
  name: "testEditComponent",
  data: function(){
    return {
      testRelTestcaseList_with_name_and_desc: []
    }
  },
  created: function() {
    var TEST_SEQ = this.$route.params.TEST_SEQ

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
      })
    })
  }
}
</script>

<style scoped>

</style>