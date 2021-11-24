
--TRIGGER
CREATE TRIGGER student_afterinsert
AFTER INSERT ON student
FOR EACH ROW
BEGIN
	INSERT INTO user(`username`)
	VALUES (new.sno);
END;


CREATE TRIGGER student_afterdelete
AFTER DELETE ON student
FOR EACH ROW
BEGIN
	DELETE FROM `user`
	WHERE `user`.username=old.sno;
END;