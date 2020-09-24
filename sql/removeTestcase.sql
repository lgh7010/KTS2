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

-- 아래의 예는, 위 상황에서 TESTCASE_SEQ가 2인 테스트케이스를 삭제하는 상황을 모델링했다.

-- step 1. 해당 테스트케이스를 삭제하면 영향을 받는 테스트 조회 -> 리스트로 메모리에 저장
SELECT TEST_SEQ FROM TEST_REL_TESTCASE WHERE TESTCASE_SEQ = 2 GROUP BY TEST_SEQ;

-- step 2. 해당 관계 삭제
DELETE FROM TEST_REL_TESTCASE WHERE TESTCASE_SEQ = 2;

-- step 3. 1에서 저장한 '영향을 받은' 테스트를 구성하는 관계의 SEQ 조회 (TEST_SEQ, INDEX)를 기준으로 정렬하되, 인덱스는 다시 매겨야 하므로 인덱스는 제외하고 조회 -> 리스트로 메모리에 저장
SELECT RELATION_SEQ FROM TEST_REL_TESTCASE WHERE TEST_SEQ IN (1, 2) ORDER BY TEST_SEQ, LIST_INDEX;

-- step 4. 아예 테이블을 새로 넣기 위해, LIST_INDEX를 제외하고 그대로 조회 -> 리스트로 메모리에 저장
SELECT RELATION_SEQ, TEST_SEQ, TESTCASE_SEQ FROM TEST_REL_TESTCASE WHERE TEST_SEQ IN (1, 2) ORDER BY TEST_SEQ, LIST_INDEX;

-- step 5. 3에서 나온 RELATION_SEQ에 해당하는 로우 전부 삭제
DELETE FROM TEST_REL_TESTCASE WHERE RELATION_SEQ IN (1, 6, 10, 7);

-- step 6. 4에서 조회해 저장했던 테이블 그대로 INSERT. 이 때, 하나의 TEST_SEQ마다 LIST_INDEX를 1에서부터 하나씩 증가시켜서 넣음.
INSERT INTO TEST_REL_TESTCASE (RELATION_SEQ, TEST_SEQ, TESTCASE_SEQ, LIST_INDEX) VALUES
(1, 1, 1, 1),
(6, 1, 3, 2),
(10, 2, 3, 1),
(7, 2, 1, 2);

-- 결과 확인
SELECT * FROM TEST_REL_TESTCASE ORDER BY TEST_SEQ, LIST_INDEX;