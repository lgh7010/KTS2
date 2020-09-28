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