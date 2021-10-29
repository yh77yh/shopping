drop table notice;

CREATE TABLE notice(
  noticeno      number(11)                NOT NULL primary key,
  title         VARCHAR2(300)             NOT NULL ,
  content       clob                      NOT NULL ,
  wname         VARCHAR2 (20)             NOT NULL ,
  passwd        VARCHAR2 (20)             NULL ,
  cnt           number(10)  DEFAULT 0     NOT NULL ,
  rdate         DATE                      NOT NULL   
);

COMMENT on column notice.noticeno is '글 번호';
COMMENT on column notice.title is '제목';
COMMENT on column notice.content is '내용';
COMMENT on column notice.wname is '작성자';
COMMENT on column notice.passwd is '패스워드';
COMMENT on column notice.cnt is '조회수';
COMMENT on column notice.rdate is '등록일';
-- 등록
INSERT INTO notice(noticeno, title, content, wname, passwd, cnt, rdate)
VALUES((select nvl(max(noticeno),0)+1 from notice), '공지사항1', '첫번째 공지사항입니다.', '개발자1', '1234', 0, sysdate);
INSERT INTO notice(noticeno, title, content, wname, passwd, cnt, rdate)
VALUES((select nvl(max(noticeno),0)+1 from notice), 'spring 시즌 접수 안내 1', '10% 할인 실시!', '왕눈이', '1234', 0, sysdate);
INSERT INTO notice(noticeno, title, content, wname, passwd, cnt, rdate)
VALUES((select nvl(max(noticeno),0)+1 from notice), 'spring 시즌 접수 안내 2', '10% 할인 실시!', '아로미', '1234', 0, sysdate);
INSERT INTO notice(noticeno, title, content, wname, passwd, cnt, rdate)
VALUES((select nvl(max(noticeno),0)+1 from notice), 'spring 시즌 접수 안내 3', '10% 할인 실시!', '투투투', '1234', 0, sysdate);
-- 목록
SELECT noticeno, title, content, wname, passwd, cnt, rdate
FROM notice
ORDER BY noticeno DESC;
-- 조회
SELECT noticeno, title, content, wname, passwd, cnt, rdate
FROM notice
WHERE noticeno=1;
-- 조회수증가
UPDATE notice
SET cnt = cnt + 1
WHERE noticeno=1;
-- 수정
UPDATE notice
SET title='추가 공지', content='5% 추가 할인!', wname='아로미'
WHERE noticeno=1;
-- 패스워드 확인
SELECT COUNT(*) AS cnt
FROM notice
WHERE noticeno=1 AND passwd='1234';
-- 삭제
DELETE FROM notice
WHERE noticeno=3;
 
SELECT * FROM notice;
