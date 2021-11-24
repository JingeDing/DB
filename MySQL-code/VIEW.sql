--VIEW
CREATE VIEW class_info(department_no,department_name,class_no,class_name,class_enterYear,class_major,class_totalYear)
AS SELECT department.dno,department.dname,class.cno,class.cname,class.`year`,class.major,class.cyear
FROM class,department WHERE class.dno=department.dno

SELECT * FROM class_info

---------------------------------------

CREATE VIEW course_info(course_no,course_name,course_semester,course_teacher,course_credit,course_weekday,course_time)
AS SELECT `no`,`name`,`semester`,`tname`,`credit`,`weekday`,`ctime`
FROM course,teacher
WHERE course.tno=teacher.tno

SELECT * FROM course_info

---------------------------------------

CREATE VIEW student_course_info(student_no,course_no,course_semester,course_teacher,course_credit,course_weekday,course_time,course_grade,course_gpa)
AS SELECT sc.sno,sc.`no`,course.semester,tname,credit,sc.weekday,sc.ctime,sc.grade,sc.gpa
FROM sc,teacher,course
WHERE  course.tno=teacher.tno AND sc.no=course.no 

SELECT * FROM student_course_info