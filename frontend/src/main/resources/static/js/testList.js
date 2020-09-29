function removeTest(button){
    console.log("removeTest : " + button.value);
    $.ajax({
        url : "/removeTest",
        type : "POST",
        data : {
            TEST_SEQ: button.value
        },
        success : function(successMsg){
            location.reload();
        },
        error : function(errMsg){
            console.error(errMsg);
        }
    })
}
function openEditLayer(button){
    console.log("openEditLayer : " + button.value);
    $.ajax({
        url : "/testRelTestcaseList",
        type : "POST",
        data : {
            TEST_SEQ: button.value
        },
        success : function(testRelTestcaseList){
            $.ajax({
                url : "/testcaseDic",
                type : "POST",
                data : {
                    TEST_SEQ: button.value
                }, success : function(testcaseDic){
                    $("#testEditLayer").show();
                    setTestcaseListVue(testRelTestcaseList, testcaseDic);
                }, error : function(err){
                    console.error(err);
                }
            })
        },
        error : function(err){
            console.error(err);
        }
    });
}
function closeEditLayer(){
    $("#testEditLayer").hide();
}



var testcaseListVue = null
function setTestcaseListVue(testRelTestcaseList, testcaseDic){
    $("#testRelTestcaseList").show();
    var list = [];
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

    if(testcaseListVue == null){
        testcaseListVue = new Vue({
            el : "#testRelTestcaseList",
            data : {
                testRelTestcaseList_with_name_and_desc : list
            }
        })
    } else {
        testcaseListVue.testRelTestcaseList_with_name_and_desc = list;
    }
}




function moveUpTestcase(button){
    var RELATION_SEQ = $(button).attr("RELATION_SEQ");
    console.log(RELATION_SEQ);
}
function moveDownTestcase(button){
    var RELATION_SEQ = $(button).attr("RELATION_SEQ");
    console.log(RELATION_SEQ);
}
function editTestcase(button){
    var RELATION_SEQ = $(button).attr("RELATION_SEQ");
    console.log(RELATION_SEQ);
}
function removeTestcaseFromTest(button){
    var RELATION_SEQ = $(button).attr("RELATION_SEQ");
    console.log(RELATION_SEQ);
}