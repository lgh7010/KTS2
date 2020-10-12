<template>
  <div>

    <div id="actionEditorBackground" style="
        display: none;
        width: 100vw;
        height: 100vh;
        position: fixed;
        background-color:gray;
        opacity: 0.5;
        z-index:10;"/>

    <div id="actionEditor" style="
        display: none;
        width: 500px;
        height: 500px;
        position: fixed;
        left: 50%;
        top: 50%;
        margin-left: -250px;
        margin-top: -250px;
        background-color: white;
        z-index:11;">
      이게 액션에디터의 내용이다옹
      <button v-on:click="actionClose">닫기</button>
    </div>

    <div id="flowchart">
      <div v-for="action in this.actionDic" v-bind:id="action.action_SEQ" style="
        width:400px;
        height:100px;
        background-color:gray;
        position: absolute;">
        <button v-on:click="actionOpen(action)">편집</button>
        <button>삭제</button>
        <hr>
        {{action.description}}
      </div>
      <button v-on:click="goBack()">닫기</button>
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
      currentAction: null
    }
  },
  created: function(){
  },
  mounted: function(){
    var TESTCASE_SEQ = this.$route.params.TESTCASE_SEQ
    axios.get("/actionDic", { params: {'TESTCASE_SEQ': TESTCASE_SEQ}}).then(response => {
      this.actionDic = response.data.context.actionDic
    }).catch(error => {
      console.log(error)
    })
  },
  updated: function() {
    for(let ACTION_SEQ in this.actionDic){
      var action =this.actionDic[ACTION_SEQ]
      var elem = document.getElementById(action.action_SEQ)
      this.registDragElement(elem)
      elem.style.top = action.y_POS + "px"
      elem.style.left = action.x_POS + "px"
    }
  },
  methods: {
    goBack: function(){
      this.$router.go(-1)
    },
    registDragElement: function(elmnt){
      var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0
      elmnt.onmousedown = dragMouseDown

      function dragMouseDown(e){
        e = e || window.event;
        e.preventDefault();
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
      }
      function elementDrag(e){
        e = e || window.event;
        e.preventDefault();
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
      }
      function closeDragElement(){
        // stop moving when mouse button is released:
        document.onmouseup = null;
        document.onmousemove = null;
      }
    },
    actionOpen: function(action){
      this.currentAction = action

      $("#actionEditorBackground").show()
      $("#actionEditor").show()
    },
    actionClose: function(){
      this.currentAction = null

      $("#actionEditorBackground").hide()
      $("#actionEditor").hide()
    }
  }
}
</script>

<style scoped>

</style>