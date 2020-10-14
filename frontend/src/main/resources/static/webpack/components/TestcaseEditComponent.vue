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
          <option v-for="actionTemplete in this.actionTempleteDic" v-bind:value="actionTemplete.action_ID">
            {{actionTemplete.action_ID}}
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
              <td>{{prop.property_NAME}}</td>
              <td><input type="text"
                         v-bind:id="prop.property_SEQ" v-bind:name="prop.property_NAME" v-bind:value="prop.property_VALUE"
                         v-on:input="onPropertyChange(prop)"></td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>

    <div id="flowchart">
      <button v-on:click="onClickSave()">저장</button>
      <button v-on:click="onClickAdd()">추가</button>
      <button v-on:click="goBack()">닫기</button>

      <div v-for="action in this.actionDic"
           v-bind:id="action.action_GUID" v-bind:action_GUID="action.action_GUID" v-bind:next_ACTION_GUID="action.next_ACTION_GUID"
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
      <line v-for="action in this.actionDic" v-if="action.next_ACTION_GUID != 'None'"
            v-bind:id="'arrow_'+action.action_GUID+'_'+action.next_ACTION_GUID"
            v-bind:action_GUID="action.action_GUID" v-bind:next_ACTION_GUID="action.next_ACTION_GUID"
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

let NULL_ACTION_NODE_GUID = "None"
let ACTION_NODE_WIDTH_HALF = 200
let ACTION_NODE_HEIGHT = 100
let DEFAULT_NODE_TOP = 500
let DEFAULT_NODE_LEFT = 500

export default {
  name: "TestcaseEditComponent",
  data(){
    return {
      actionDic: {},//현재 페이지에서 관리중인 액션노드의 딕셔너리. 키값은 ACTION_GUID
      actionTempleteDic: {},//액션 템플릿의 딕셔너리. 최초 페이지 생성시 불러온다.
      currentTestcaseSeq: this.$route.params.TESTCASE_SEQ,//현재 이 에디터에서 편집중인 테스트케이스의 시퀀스

      arrows_start_map: {},//키는 시작 action_GUID, 값은 끝 action_GUID
      arrows_end_map: {},//키는 끝 action_GUID, 값은 시작 action_GUID
      removeActionGuidList: [],//페이지상에서 삭제되었지만 아직 DB에 반영되지 않은 액션노드 정보들

      actionProperties: [],//액션 에디터에 나타날 프로퍼티들
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
      console.log(this.actionDic)
    }).catch(error => {
      console.log(error)
    })
  },
  updated(){
    for(let ACTION_GUID in this.actionDic){
      var action = this.actionDic[ACTION_GUID]
      var node = document.getElementById(action.action_GUID)

      //노드 등록 및 위치 설정
      node.style.top = action.y_POS + "px"
      node.style.left = action.x_POS + "px"

      //드래그 설정
      this.registDragableNode(action, node, this.arrows_start_map, this.arrows_end_map)
      this.registDragableArrow(document.getElementById("arrow_" + action.action_GUID + "_" + action.next_ACTION_GUID))

      //최초 위치 설정
      this.nodePositionSet(action, node, action.y_POS, action.x_POS)
    }
  },
  methods: {
    //제어 관련
    goBack(){
      this.$router.go(-1)
    },
    getFirstAction(){
      for(let ACTION_GUID in this.actionDic){
        if(this.actionDic[ACTION_GUID].is_START == 'Y'){
          return this.actionDic[ACTION_GUID]
        }
      }
      return null
    },
    getLastAction(){
      for(let ACTION_GUID in this.actionDic){
        if(this.actionDic[ACTION_GUID].next_ACTION_GUID == NULL_ACTION_NODE_GUID){
          return this.actionDic[ACTION_GUID]
        }
      }
      return null
    },
    onClickAdd(){
      function guid() {
        function _p8(s) {
          var p = (Math.random().toString(16)+"000000000").substr(2,8);
          return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
        }
        return _p8() + _p8(true) + _p8(true) + _p8();
      }
      var lastAction = this.getLastAction()
      var guid = guid()
      lastAction.next_ACTION_GUID = guid;
      Vue.set(this.actionDic, guid, {
        action_GUID: guid,
        testcase_SEQ: this.currentTestcaseSeq,
        is_START: 'N',
        next_ACTION_GUID: NULL_ACTION_NODE_GUID,
        action_ID: 'Empty',
        x_POS: DEFAULT_NODE_LEFT,
        y_POS: DEFAULT_NODE_TOP,
        description: "",
        deleted: 'N',
      })
    },
    onClickSave(){
      console.log(this.actionDic)

      axios.post("/saveActionDic", {
        "ACTION_DIC": this.actionDic,
        "REMOVE_ACTION_GUID_LIST": this.removeActionGuidList
      }).then(response => {
        alert("저장 완료")
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    onClickRemove(action){
      //현재 노드의 이전 노드의 next_ACTION_GUID를 현재 노드의 다음 노드로 세팅(다음노드가 없다면 0으로 세팅)
      var beforeActionGuid = this.arrows_end_map[action.action_GUID]
      if(beforeActionGuid){
        var nextActionGuid = this.arrows_start_map[action.action_GUID]
        this.actionDic[beforeActionGuid].next_ACTION_GUID = (nextActionGuid) ? nextActionGuid : NULL_ACTION_NODE_GUID
      }

      this.removeActionGuidList.push(action.action_GUID)

      Vue.delete(this.actionDic, action.action_GUID)
    },

    //SVG 관련
    registDragableArrow(arrow){
      if(!arrow){
        return
      }

      var jarrow = $(arrow)
      this.arrows_start_map[jarrow.attr('action_GUID')] = jarrow.attr('next_ACTION_GUID')
      this.arrows_end_map[jarrow.attr('next_ACTION_GUID')] = jarrow.attr('action_GUID')
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
      action.x_POS = newLeft
      action.y_POS = newTop
      node.style.top = newTop + "px"
      node.style.left = newLeft + "px"

      //들어오는 화살표 위치 조정
      var startAt = this.arrows_end_map[jnode.attr('action_GUID')]
      var endAt = jnode.attr('action_GUID')
      var jarrow = $("#arrow_" + startAt + "_" + endAt)
      jarrow.attr('x2', newLeft + ACTION_NODE_WIDTH_HALF)
      jarrow.attr('y2', newTop - ACTION_NODE_HEIGHT)

      //나가는 화살표 위치 조정
      startAt = jnode.attr('action_GUID')
      endAt = this.arrows_start_map[jnode.attr('action_GUID')]
      jarrow = $("#arrow_" + startAt + "_" + endAt)
      jarrow.attr('x1', newLeft + ACTION_NODE_WIDTH_HALF)
      jarrow.attr('y1', newTop)
    },

    //액션노드 편집 관련
    actionOpen(action){
      //현재 해당 노드에 포함된 속성정보를 불러온다.
      axios.get("/properties", { params: {'ACTION_GUID': action.action_GUID}}).then(properties => {
        this.actionProperties = properties.data.context.properties

        $("#actionEditorBackground").show()
        $("#actionEditor").show()
      }).catch(error => {
        console.log(error)
      })
    },
    actionClose(){
      $("#actionEditorBackground").hide()
      $("#actionEditor").hide()
    },
    onChangeActionID(){
      //해당 액션의 템플릿의 속성정보를 불러온다.
      axios.get("/propertiesTemplete", { params: {'ACTION_ID': $("#actionIdSelection").val()}}).then(templeteProperties => {
        this.actionProperties = templeteProperties.data.context.templeteProperties
      }).catch(error => {
        console.log(error)
      })
    },
    onClickActionSave(){
      axios.post("/saveProperties", {
        "PROPERTIES": this.actionProperties
      }).then(response => {
        alert('저장 완료')
        console.log(response)
      }).catch(error => {
        console.log(error)
      })
    },
    onPropertyChange(prop){
      var index = this.actionProperties.indexOf(prop)
      this.actionProperties[index].property_VALUE = document.getElementById(prop.property_SEQ).value
    }
  }
}
</script>

<style scoped>

</style>