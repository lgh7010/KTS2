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

      <div>
        <select id="actionIdSelection" @change="onChangeActionID()">
          <option v-for="actionTemplate in this.actionTemplates" v-bind:value="actionTemplate.actionId">
            {{actionTemplate.actionId}}
          </option>
        </select>
        <button v-on:click="onClickActionSave">저장</button>
        <button v-on:click="actionClose">닫기</button>
      </div>

      <div>
        <table>
          <thead>
            <tr>
              <th>속성</th>
              <th>값</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="prop in this.actionProperties">
              <td>{{prop.propertyName}}</td>
              <td><input type="text" v-bind:id="prop.propertySeq" v-bind:value="prop.propertyValue" v-on:input="onPropertyChange(prop)"></td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>

    <div id="flowchart">
      <td><input id="testcaseName" type="text"></td>
      <td><input id="testcaseDescription" type="text"></td>
      <button v-on:click="onClickTestcaseSave()">저장</button>
      <button v-on:click="onClickAdd()">추가</button>
      <button v-on:click="goBack()">닫기</button>

      <div v-for="action in this.currentTestcaseActions"
           v-bind:id="action.actionGuid" v-bind:actionGuid="action.actionGuid" v-bind:nextActionGuid="action.nextActionGuid"
           class="node" style="width:400px;
        height:100px;
        background-color:gray;
        position: absolute;">
        <button v-on:click="actionOpen(action)">편집</button>
        <button v-on:click="onClickRemove(action)">삭제</button>
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
      <line v-for="action in this.currentTestcaseActions" v-if="action.nextActionGuid != 'None'"
            v-bind:id="'arrow_'+action.actionGuid+'_'+action.nextActionGuid"
            v-bind:actionGuid="action.actionGuid" v-bind:nextActionGuid="action.nextActionGuid"
            v-bind:x1="action.x+200" v-bind:y1="action.y"
            v-bind:x2="action.x+200" v-bind:y2="action.y+100"
            class="arrow" stroke-width="7" stroke="black" marker-end="url(#arrow)"/>
    </svg>

  </div>
</template>

<script>
import jQuery from 'jquery'
import axios from "axios";
import Vue from 'vue'
window.jQuery = window.$ = jQuery

let NULL_ACTION_NODE_GUID = "None"
let ACTION_NODE_WIDTH_HALF = 200
let ACTION_NODE_HEIGHT = 100
let DEFAULT_NODE_TOP = 500
let DEFAULT_NODE_LEFT = 500

export default {
  name: "TestcaseEditComponent",
  data(){
    return {
      currentTestcaseActions: {},//현재 페이지에서 관리중인 액션노드의 딕셔너리. 키값은 ACTION_GUID
      actionTemplates: {},//액션 템플릿의 딕셔너리. 최초 페이지 생성시 불러온다.
      currentTestcase: null,//현재 에디터에서 편집중인 테스트케이스

      arrowsStartMap: {},//키는 시작 action_GUID, 값은 끝 action_GUID
      arrowsEndMap: {},//키는 끝 action_GUID, 값은 시작 action_GUID
      removeActionGuidList: [],//페이지상에서 삭제되었지만 아직 DB에 반영되지 않은 액션노드 정보들

      currentActionGuid: "",
      actionProperties: [],//액션 에디터에 나타날 프로퍼티들
    }
  },
  created(){
    axios.get("/actionTemplates").then(response => {
      console.log(response)
      this.actionTemplates = response.data.context.actionTemplates
    }).catch(error => {
      console.log(error)
    })
  },
  mounted(){
    axios.get("/currentTestcaseActions", { params: {'testcaseGuid': this.$route.params.testcaseGuid}}).then(responseCurrentTestcaseActions => {
      axios.get("/testcase", { params: {'testcaseGuid': this.$route.params.testcaseGuid}}).then(responseTestcase => {
        console.log(responseTestcase)
        this.currentTestcase = (responseTestcase.data.context != null && responseTestcase.data.context.testcase != null) ? responseTestcase.data.context.testcase : {
          testcaseGuid: this.guid(),
          name: "",
          description: "",
        }

        $("#testcaseName").val(this.currentTestcase.name)
        $("#testcaseDescription").val(this.currentTestcase.description)
        this.currentTestcaseActions = responseCurrentTestcaseActions.data.context.currentTestcaseActions
      }).catch(error => {
        console.log(error)
      })
    }).catch(error => {
      console.log(error)
    })
  },
  updated(){
    for(let actionGuid in this.currentTestcaseActions){
      var action = this.currentTestcaseActions[actionGuid]
      var node = document.getElementById(action.actionGuid)

      //노드 등록 및 위치 설정
      node.style.top = action.y + "px"
      node.style.left = action.x + "px"

      //드래그 설정
      this.registDragableNode(action, node, this.arrowsStartMap, this.arrowsEndMap)
      this.registDragableArrow(document.getElementById("arrow_" + action.actionGuid + "_" + action.nextActionGuid))

      //최초 위치 설정
      this.nodePositionSet(action, node, action.y, action.x)
    }
  },
  methods: {
    guid(){
      function _p8(s) {
        var p = (Math.random().toString(16)+"000000000").substr(2,8);
        return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
      }
      return _p8() + _p8(true) + _p8(true) + _p8();
    },

    //제어 관련
    goBack(){
      this.currentTestcase = null
      this.$router.go(-1)
    },
    getFirstAction(){
      for(let actionGuid in this.currentTestcaseActions){
        if(this.currentTestcaseActions[actionGuid].isStart == 'Y'){
          return this.currentTestcaseActions[actionGuid]
        }
      }
      return null
    },
    getLastAction(){
      for(let actionGuid in this.currentTestcaseActions){
        if(this.currentTestcaseActions[actionGuid].nextActionGuid == NULL_ACTION_NODE_GUID){
          return this.currentTestcaseActions[actionGuid]
        }
      }
      return null
    },
    onClickAdd(){
      var lastAction = this.getLastAction()
      var guid = this.guid()
      if(lastAction != null){
        lastAction.nextActionGuid = guid;
      }
      Vue.set(this.currentTestcaseActions, guid, {
        actionGuid: guid,
        testcaseGuid: this.currentTestcase.testcaseGuid,
        isStart: 'N',
        nextActionGuid: NULL_ACTION_NODE_GUID,
        actionId: 'Empty',
        x: DEFAULT_NODE_LEFT,
        y: DEFAULT_NODE_TOP,
        description: "",
        deleted: 'N',
      })
    },
    onClickTestcaseSave(){
      this.currentTestcase.name = $("#testcaseName").val()
      this.currentTestcase.description = $("#testcaseDescription").val()
      console.log(this.currentTestcase)
      axios.post("/saveCurrentTestcaseActions", {
        "currentTestcaseActions": this.currentTestcaseActions,
        "removeActionGuidList": this.removeActionGuidList,
        "testcase": this.currentTestcase,
      }).then(response => {
        alert("저장 완료")
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    onClickRemove(action){
      //현재 노드의 이전 노드의 next_ACTION_GUID를 현재 노드의 다음 노드로 세팅(다음노드가 없다면 0으로 세팅)
      var beforeActionGuid = this.arrowsEndMap[action.actionGuid]
      if(beforeActionGuid && this.currentTestcaseActions[beforeActionGuid]){
        var nextActionGuid = this.arrowsStartMap[action.actionGuid]
        this.currentTestcaseActions[beforeActionGuid].nextActionGuid = (nextActionGuid) ? nextActionGuid : NULL_ACTION_NODE_GUID
      }
      this.removeActionGuidList.push(action.actionGuid)
      Vue.delete(this.currentTestcaseActions, action.actionGuid)
    },

    //SVG 관련
    registDragableArrow(arrow){
      if(!arrow){
        return
      }
      var jarrow = $(arrow)
      this.arrowsStartMap[jarrow.attr('actionGuid')] = jarrow.attr('nextActionGuid')
      this.arrowsEndMap[jarrow.attr('nextActionGuid')] = jarrow.attr('actionGuid')
    },
    registDragableNode(action, node){
      var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0
      var self = this
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
        self.nodePositionSet(action, node, (node.offsetTop - pos2), (node.offsetLeft - pos1))
      }
      function closeDragElement(){
        // stop moving when mouse button is released:
        document.onmouseup = null
        document.onmousemove = null
      }
    },
    nodePositionSet(action, node, newTop, newLeft) {
      var jnode = $(node)
      action.x = newLeft
      action.y = newTop
      node.style.top = newTop + "px"
      node.style.left = newLeft + "px"

      //들어오는 화살표 위치 조정
      var startAt = this.arrowsEndMap[jnode.attr('actionGuid')]
      var endAt = jnode.attr('actionGuid')
      var jarrow = $("#arrow_" + startAt + "_" + endAt)
      jarrow.attr('x2', newLeft + ACTION_NODE_WIDTH_HALF)
      jarrow.attr('y2', newTop - ACTION_NODE_HEIGHT)

      //나가는 화살표 위치 조정
      startAt = jnode.attr('actionGuid')
      endAt = this.arrowsStartMap[jnode.attr('actionGuid')]
      jarrow = $("#arrow_" + startAt + "_" + endAt)
      jarrow.attr('x1', newLeft + ACTION_NODE_WIDTH_HALF)
      jarrow.attr('y1', newTop)
    },

    //액션노드 편집 관련
    actionOpen(action){
      //현재 해당 노드에 포함된 속성정보를 불러온다.
      this.currentActionGuid = action.actionGuid
      axios.get("/properties", { params: {'actionGuid': this.currentActionGuid}}).then(response => {
        this.actionProperties = response.data.context.list
        console.log(action);
        $("#actionIdSelection").val(action.actionId)

        $("#actionEditorBackground").show()
        $("#actionEditor").show()
      }).catch(error => {
        console.log(error)
      })
    },
    actionClose(){
      this.currentActionGuid = ""
      $("#actionEditorBackground").hide()
      $("#actionEditor").hide()
    },
    onChangeActionID(){
      //해당 액션의 템플릿의 속성정보를 불러온다.
      var actionId = $("#actionIdSelection").val()
      this.currentTestcaseActions[this.currentActionGuid].actionId = actionId
      axios.get("/propertiesTemplate", { params: {'actionId': actionId}}).then(response => {
        console.log(response)
        this.actionProperties = response.data.context.list
        //템플릿이라서 현재 property_SEQ값이 없다. actionProperties에 템플릿 리스트나 그냥 프로퍼티 리스트 둘 다 들어갈 수 있는데서 비롯된 문제.
        //그냥 여기서 넣어준다. 0으로 넣어주면 된다.
        for(var i = 0; i < this.actionProperties.length; i++){
          this.actionProperties[i].propertySeq = 0
          this.actionProperties[i].actionGuid = this.currentActionGuid
        }
      }).catch(error => {
        console.log(error)
      })
    },
    onClickActionSave(){
      axios.post("/saveProperties", {
        "actionGuid": this.currentActionGuid,
        "actionId": this.currentTestcaseActions[this.currentActionGuid].actionId,
        "properties": this.actionProperties
      }).then(response => {
        alert('저장 완료')
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    onPropertyChange(prop){
      prop.propertyValue = document.getElementById(prop.propertySeq).value
    }
  }
}
</script>

<style scoped>

</style>