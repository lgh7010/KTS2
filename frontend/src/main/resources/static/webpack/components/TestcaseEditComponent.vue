<template>
  <div>
    <div>
      <table>
        <thead>
        <tr>
          <th>액션ID</th>
          <th>설명</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="action in this.actionDic">
          <td>{{action.action_ID}}</td>
          <td>{{action.description}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <hr>
    <button v-on:click="goBack()">닫기</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TestcaseEditComponent",
  data: function(){
    return {
      actionDic: {}
    }
  },
  mounted: function(){
    var TESTCASE_SEQ = this.$route.params.TESTCASE_SEQ

    axios.get("/actionDic", { params: {'TESTCASE_SEQ': TESTCASE_SEQ}}).then(response => {
      this.actionDic = response.data.context.actionDic
    }).catch(error => {
      console.log(error)
    })
  },
  methods: {
    goBack: function(){
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>

</style>