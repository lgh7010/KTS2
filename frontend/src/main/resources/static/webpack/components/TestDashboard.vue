<template>
  <div>

    진행중인 테스트
    <div class="container-fluid">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>테스트 이름</th>
          <th>설명</th>
          <th>시작 시간</th>
          <th>진행 시간</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="runningTest in this.runningTestList">
          <td>{{runningTest.name}}</td>
          <td>{{runningTest.description}}</td>
          <td>{{runningTest.startAt}}</td>
          <td>{{runningTest.endAt}}</td>
        </tr>
        </tbody>
      </table>
    </div>

    완료된 테스트
    <div class="container-fluid">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>테스트 이름</th>
          <th>설명</th>
          <th>시작 시간</th>
          <th>완료 시간</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="finishedTest in this.finishedTestList">
          <td>{{finishedTest.name}}</td>
          <td>{{finishedTest.description}}</td>
          <td>{{finishedTest.startAt}}</td>
          <td>{{finishedTest.endAt}}</td>
        </tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script>
import jQuery from 'jquery'
import axios from "axios";
window.jQuery = window.$ = jQuery

export default {
  name: "TestDashboard",
  data(){
    return {
      runningTestList: [],
      finishedTestList: [],
    }
  },
  mounted() {
    axios.get("/findAllRunningTest").then(response => {
      this.runningTestList = []
      this.finishedTestList = []
      var tempList = response.data.context.runningTests
      for(let index in tempList){
        if(tempList[index].status == "Running"){
          this.runningTestList.push(tempList[index])
        } else {
          this.finishedTestList.push(tempList[index])
        }
      }
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>

<style scoped>

</style>