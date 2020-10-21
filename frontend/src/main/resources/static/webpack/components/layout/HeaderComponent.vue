<template>
  <div class="navbar-nav bg-gray-600 sidebar-dark accordion">
    <table>
      <thead>
        <th>
          <router-link :to="{name: 'Home'}" class="nav-item"><a class="nav-link"><span>홈으로</span></a></router-link>
        </th>
        <th>
          <router-link :to="{name: 'TestList'}" class="nav-item"><a class="nav-link"><span>테스트</span></a></router-link>
        </th>
        <th>
          <router-link :to="{name: 'TestcaseList'}" class="nav-item"><a class="nav-link"><span>테스트케이스</span></a></router-link>
        </th>
        <th>
          <button v-on:click="this.runTest">임시클라이언트 테스트 시작</button>
          <button v-on:click="this.finishAction">액션 하나 강제 완료</button>
        </th>
      </thead>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HeaderComponent",
  data() {
    return {
      testList: [],
      currentRunningTest: {},
    }
  },
  methods: {
    runTest(){
      axios.get('/findAllTest').then(response => {
        this.testList = response.data.context.list

        if(this.testList.length > 0){
          axios.post("/runTest", {
            "testGuid": this.testList[0].testGuid
          }).then(response => {
            console.log(response)
            this.currentRunningTest = response.data.context.instruction.runningTest
          }).catch(error => {
            console.log(error)
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    finishAction(){
      axios.post("/onFinishAction", {
        runningTestGuid: this.currentRunningTest.runningTestGuid,
        status: 'Success',
      }).then(response => {
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>