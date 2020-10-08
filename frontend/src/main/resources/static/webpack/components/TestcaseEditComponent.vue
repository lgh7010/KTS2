<template>
  <div>
    <div id="flowchart">
<!--      SVG를 아예 빼버리고 그냥 div에다가 클래스 + 디자인 부여한다음에 처리해도 될듯-->
      <svg ref="flowchartSVG" class="flowchartSVG" width="100%" height="1000">
        <rect width="100%" height="100%" fill="white" stroke="black" stroke-width="5"/>
        <rect v-for="action in this.actionDic"
              width="400" height="100" fill="gray"
              :x="action.x_POS" :y="action.y_POS" :style="cursor"
              @mousedown="drag" @mouseup="drop" @mouseenter="enter(action)"
              v-on:click="actionOpen(action)"
        />
        <text v-for="action in this.actionDic"
              :x="action.x_POS" :y="action.y_POS+30" fill="black"
        >{{action.action_ID}}</text>
        <text v-for="action in this.actionDic"
              :x="action.x_POS" :y="action.y_POS+70" fill="black"
        >{{action.description}}</text>
      </svg>
      <button v-on:click="goBack()">닫기</button>
    </div>

    <div id="actionEditor" class="actionEditor" style="display: none">
      이게 액션에디터의 내용이다옹
      <button v-on:click="actionClose">닫기</button>
    </div>
  </div>
</template>

<script>
import jQuery from 'jquery'
import axios from "axios";
window.jQuery = window.$ = jQuery

export default {
  name: "TestcaseEditComponent",
  data: function(){
    return {
      actionDic: {},
      currentMouseOnActionSeq: 0,
      currentAction: null,
      dragOffsetX: null,
      dragOffsetY: null
    }
  },
  computed: {
    cursor: function(){
      return `cursor: ${this.dragOffsetX ? 'grabbing' : 'grab'}`
    }
  },
  created: function(){
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
    enter: function(action){
      this.currentMouseOnActionSeq = action.action_SEQ
    },
    drag: function({offsetX, offsetY}){
      this.dragOffsetX = offsetX - this.actionDic[this.currentMouseOnActionSeq].x_POS
      this.dragOffsetY = offsetY - this.actionDic[this.currentMouseOnActionSeq].y_POS
      this.$refs.flowchartSVG.addEventListener('mousemove', this.move)
    },
    drop: function(){
      this.dragOffsetX = this.dragOffsetY = null
      this.$refs.flowchartSVG.removeEventListener('mousemove', this.move)
    },
    move: function({offsetX, offsetY}){
      this.actionDic[this.currentMouseOnActionSeq].x_POS = offsetX - this.dragOffsetX
      this.actionDic[this.currentMouseOnActionSeq].y_POS = offsetY - this.dragOffsetY
    },
    actionOpen: function(action){
      this.currentAction = action

      $("#actionEditor").show()
    },
    actionClose: function(){
      this.currentAction = null

      $("#actionEditor").hide()
    }
  }
}
</script>

<style scoped>
.flowchartSVG {
  margin: auto;
  position: center;
  display: block;
}
.actionEditor {
  width: 400px;
  height: 100px;
  position: absolute;
  left: 50%;
  top: 50%;
  z-index:300;
}
</style>