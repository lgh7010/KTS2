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