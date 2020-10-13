<template>
  <div>

    <div id="actionEditorBackground"
         style="display: none;
        width: 100vw;
        height: 100vh;
        position: fixed;
        background-color:gray;
        opacity: 0.5;
        z-index:10;"/>

    <div id="actionEditor"
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
      이게 액션에디터의 내용이다옹
      <button v-on:click="actionClose">닫기</button>
    </div>

    <div id="flowchart">
      <button v-on:click="goBack()">닫기</button>
      <button v-on:click="onClickAdd()">추가</button>

      <div v-for="action in this.actionDic"
           v-bind:id="action.action_SEQ" v-bind:action_SEQ="action.action_SEQ" v-bind:next_ACTION_SEQ="action.next_ACTION_SEQ"
           class="node" style="width:400px;
        height:100px;
        background-color:gray;
        position: absolute;">
        <button v-on:click="actionOpen(action)">편집</button>
        <button>삭제</button>
        <hr>
        {{action.description}}
      </div>
    </div>

    <svg width="1000" height="1000" viewbox="0 0 1000 1000">
      <defs>
        <marker id="arrow" viewBox="0 0 10 10" refX="5" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse">
          <path d="M 0 0 L 10 5 L 0 10 z" fill="black"/>
        </marker>
      </defs>
      <line v-for="action in this.actionDic" v-if="action.next_ACTION_SEQ != 0"
            v-bind:id="'arrow_'+action.action_SEQ+'_'+action.next_ACTION_SEQ"
            v-bind:action_SEQ="action.action_SEQ" v-bind:next_ACTION_SEQ="action.next_ACTION_SEQ"
            v-bind:x1="action.x_POS+200" v-bind:y1="action.y_POS"
            v-bind:x2="action.x_POS+200" v-bind:y2="action.y_POS+100"
            class="arrow" stroke-width="7" stroke="black" marker-end="url(#arrow)"/>
    </svg>

  </div>
</template>

<script>
import jQuery from 'jquery'
import axios from "axios";
import Vue from 'vue'
window.jQuery = window.$ = jQuery

export default {
  name: "TestcaseEditComponent",
  data(){
    return {
      actionDic: {},//현재 페이지에서 관리중인 액션노드의 딕셔너리. 키값은 ACTION_SEQ
      actionTempleteDic: {},//액션 템플릿의 딕셔너리. 최초 페이지 생성시 불러온다.
      currentTestcaseSeq: this.$route.params.TESTCASE_SEQ,//현재 이 에디터에서 편집중인 테스트케이스의 시퀀스
      currentAction: null,//현재 열려있는 액션 에디터의 주체가 되는 액션노드.
      nodes: {},//액션노드들의 html 엘리먼트를 보관하는 딕셔너리
      arrows_start_map: {},//키는 시작 action_SEQ, 값은 끝 action_SEQ
      arrows_end_map: {},//키는 끝 action_SEQ, 값은 시작 action_SEQ
      dummySeq: -1,//편집시에 추가되어서 아직 DB에 저장되지 않은 노드들에게 임시로 붙일 시퀀스. 음수값을 가져야 한다.
    }
  },
  created(){
    axios.get("/actionTempleteDic").then(response => {
      this.actionTempleteDic = response.data.context.actionTempleteDic
    }).catch(error => {
      console.log(error)
    })
  },
  mounted(){
    axios.get("/actionDic", { params: {'TESTCASE_SEQ': this.currentTestcaseSeq}}).then(response => {
      this.actionDic = response.data.context.actionDic
    }).catch(error => {
      console.log(error)
    })
  },
  updated(){
    for(let ACTION_SEQ in this.actionDic){
      //this.addNode(this.actionDic[ACTION_SEQ])
      var action = this.actionDic[ACTION_SEQ]
      var node = document.getElementById(action.action_SEQ)

      //노드 등록 및 위치 설정
      this.nodes[action.action_SEQ] = node
      node.style.top = action.y_POS + "px"
      node.style.left = action.x_POS + "px"

      //드래그 설정
      this.registDragableNode(node, this.arrows_start_map, this.arrows_end_map)
      this.registDragableArrow(document.getElementById("arrow_" + action.action_SEQ + "_" + action.next_ACTION_SEQ))
    }
  },
  methods: {
    //제어 관련
    goBack(){
      this.$router.go(-1)
    },
    getFirstAction(){
      for(let ACTION_SEQ in this.actionDic){
        if(this.actionDic[ACTION_SEQ].is_START == 'Y'){
          return this.actionDic[ACTION_SEQ]
        }
      }
      return null
    },
    getLastAction(){
      for(let ACTION_SEQ in this.actionDic){
        if(this.actionDic[ACTION_SEQ].next_ACTION_SEQ == 0){
          return this.actionDic[ACTION_SEQ]
        }
      }
      return null
    },
    onClickAdd(){
      var lastAction = this.getLastAction()
      lastAction.next_ACTION_SEQ = this.dummySeq;
      Vue.set(this.actionDic, this.dummySeq, {
        action_SEQ: this.dummySeq,
        testcase_SEQ: this.currentTestcaseSeq,
        is_START: 'N',
        next_ACTION_SEQ: 0,
        action_ID: 'Empty',
        x_POS: 500,
        y_POS: 500,
        description: this.actionTempleteDic['Empty'].templete_PROPERTY_JSON,
        deleted: 'N',
      })
      this.dummySeq--
      console.log(this.actionDic)
    },

    //SVG 관련
    registDragableArrow(arrow){
      if(!arrow){
        return
      }

      var jarrow = $(arrow)
      this.arrows_start_map[jarrow.attr('action_SEQ')] = jarrow.attr('next_ACTION_SEQ')
      this.arrows_end_map[jarrow.attr('next_ACTION_SEQ')] = jarrow.attr('action_SEQ')
    },
    registDragableNode(node, arrows_start_map, arrows_end_map){
      var jnode = $(node)
      var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0
      node.onmousedown = dragMouseDown

      function dragMouseDown(e){
        e = e || window.event
        e.preventDefault()

        // get the mouse cursor position at startup:
        pos3 = e.clientX
        pos4 = e.clientY
        document.onmouseup = closeDragElement

        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag
      }
      function elementDrag(e){
        e = e || window.event
        e.preventDefault()

        // calculate the new cursor position:
        pos1 = pos3 - e.clientX
        pos2 = pos4 - e.clientY
        pos3 = e.clientX
        pos4 = e.clientY

        // set the element's new position:
        var newTop = (node.offsetTop - pos2)
        var newLeft = (node.offsetLeft - pos1)
        node.style.top = newTop + "px"
        node.style.left = newLeft + "px"

        //들어오는 화살표 위치 조정
        var startAt = arrows_end_map[jnode.attr('action_SEQ')]
        var endAt = jnode.attr('action_SEQ')
        var jarrow = $("#arrow_" + startAt + "_" + endAt)
        jarrow.attr('x2', newLeft + 200)
        jarrow.attr('y2', newTop - 100)

        //나가는 화살표 위치 조정
        startAt = jnode.attr('action_SEQ')
        endAt = arrows_start_map[jnode.attr('action_SEQ')]
        jarrow = $("#arrow_" + startAt + "_" + endAt)
        jarrow.attr('x1', newLeft + 200)
        jarrow.attr('y1', newTop)
      }
      function closeDragElement(){
        // stop moving when mouse button is released:
        document.onmouseup = null
        document.onmousemove = null
      }
    },

    //액션노드 편집 관련
    actionOpen(action){
      this.currentAction = action

      $("#actionEditorBackground").show()
      $("#actionEditor").show()
    },
    actionClose(){
      this.currentAction = null

      $("#actionEditorBackground").hide()
      $("#actionEditor").hide()
    }
  }
}
</script>

<style scoped>

</style>