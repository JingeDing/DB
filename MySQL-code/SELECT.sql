SELECT DISTINCT course.`no`,course.semester,course.`name`,
teacher.tname,course.credit,course.weekday,course.ctime 
FROM course,sc,teacher
WHERE course.tno=teacher.tno 
AND course.`no` not IN (SELECT `no` FROM sc WHERE sc.sno=201984160204)  


SELECT sc.`no`,course.`name`,course.semester,course.credit,sc.grade,sc.gpa FROM sc,course
WHERE sc.no=course.`no` AND sc.grade IS NOT NULL AND sc.sno=201984160204

SELECT course.`no`,course.`name`,course.weekday,course.ctime
FROM sc,course
WHERE sc.sno=201984160204 AND sc.grade IS NULL AND sc.`no`=course.`no`


SELECT course.`no`,course.`name`,teacher.tno,teacher.tname,teacher.title,teacher.email,department.dname
FROM sc,course,teacher,department
WHERE sc.sno=201984160204 AND sc.grade IS NULL AND sc.`no`=course.`no`
AND teacher.tno=course.tno AND teacher.dno=department.dno


SELECT sc.no,sc.sno,student.sname,sc.grade,course.`name` FROM sc,student,course
WHERE sc.no='CSDB101' AND sc.sno=student.sno AND course.`no`=sc.`no`

SELECT department.dname,class.cno,class.cname,class.`year`,class.major,class.cyear, student.sno, student.sname,student.sex
FROM student,class,department
WHERE student.cno=class.cno AND department.dno=class.dno AND class.cno=19841602 





