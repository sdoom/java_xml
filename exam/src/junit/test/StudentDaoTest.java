package junit.test;
import entity.Student;
import dao.StudentDao;
import org.junit.Test;

import dao.StudentDao;
import entity.Student;

public class StudentDaoTest {
	@Test
	public void testAdd()
	{
		StudentDao dao = new StudentDao();
		Student s = new Student();
		s.setExamid("121");
		s.setGrade(89);
		s.setIdcard("121");
		s.setLocation("±±¾©");
		s.setName("aa");
		dao.add(s);
	}
	public void testFind()
	{
		StudentDao dao = new StudentDao();
		dao.find("111");
	}
	
}