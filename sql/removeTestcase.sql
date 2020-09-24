TRUNCATE TABLE TEST_REL_TESTCASE;
INSERT  INTO `TEST_REL_TESTCASE`(`TEST_SEQ`,`TESTCASE_SEQ`,`LIST_INDEX`) VALUES
(1,1,1),
(1,2,2),
(1,2,3),
(1,2,4),
(1,2,5),
(1,3,6),
(2,1,4),
(2,2,2),
(2,2,3),
(2,3,1);

-- step 1. 해당 테스트케이스를 삭제하면 영향을 받는 테스트 조회 -> 리스트로 메모리에 저장
SELECT TEST_SEQ FROM TEST_REL_TESTCASE WHERE TESTCASE_SEQ = 2 GROUP BY TEST_SEQ;

-- step 2. 해당 관계 삭제
DELETE FROM TEST_REL_TESTCASE WHERE TESTCASE_SEQ = 2;

-- step 3. 새로 추가해야할 로우들을 미리 생성
SET @rownum:=0;
SELECT RELATION_SEQ, TEST_SEQ, @rownum:=@rownum+1 AS LIST_INDEX, TESTCASE_SEQ FROM TEST_REL_TESTCASE WHERE TEST_SEQ = 1 ORDER BY TEST_SEQ, LIST_INDEX;
SET @rownum:=0;
SELECT RELATION_SEQ, TEST_SEQ, @rownum:=@rownum+1 AS LIST_INDEX, TESTCASE_SEQ FROM TEST_REL_TESTCASE WHERE TEST_SEQ = 2 ORDER BY TEST_SEQ, LIST_INDEX;

-- step 4. LIST_INDEX를 업데이트해야 하는 로우를 그냥 통째 전부 삭제 (step 1에서 찾은 테스트들에 속하는 모든 로우 삭제)
DELETE FROM TEST_REL_TESTCASE WHERE TEST_SEQ IN (1, 2);

-- step 5. 3에서 추출한 값을 실제로 insert
INSERT INTO TEST_REL_TESTCASE (RELATION_SEQ, TEST_SEQ, TESTCASE_SEQ, LIST_INDEX) VALUES
(1, 1, 1, 1),
(6, 1, 3, 2),
(10, 2, 3, 1),
(7, 2, 1, 2);

-- 결과 확인
SELECT * FROM TEST_REL_TESTCASE ORDER BY TEST_SEQ, LIST_INDEX;