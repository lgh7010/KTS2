<template>
  <div>
    <div>
      <svg ref="box" width="500" height="500">
        <rect width="100%" height="100%" fill="white" stroke="black" stroke-width="5"></rect>
        <rect width="100" height="100" class="square" fill="red" :x="square.x" :y="square.y" @mousedown="drag" @mouseup="drop"></rect>
      </svg>
    </div>

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
      actionDic: {},
      square: {
        x: 100,
        y: 100
      },
      dragOffsetX: null,
      dragOffsetY: null
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
    },
    drag: function({offsetX, offsetY}){
      this.$refs.box.addEventListener('mousemove', this.move)
    },
    drop: function(){
      this.$refs.box.removeEventListener('mousemove', this.move)
    },
    move: function({offsetX, offsetY}){
      this.square.x = offsetX
      this.square.y = offsetY
    }
  }
}
</script>

<style scoped>
.box {
  margin: auto;
  position: center;
  display: block;
}
</style>