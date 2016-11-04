package dao;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import utils.XmlUtils;
import entity.Student;
import exception.StudentNotExistException;

public class StudentDao {
	public void add(Student s)
	{
		try {
			Document document = XmlUtils.getDocument();
			Element student_tag = document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			
			Element name = document.createElement("name");
			Element location = document.createElement("location");
			Element grade = document.createElement("grade");
			
			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			grade.setTextContent(s.getGrade()+"");
			
			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);
			
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			XmlUtils.wreite2Xml(document);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
			// TODO Auto-generated catch block
		} 
		
	}
	public Student find(String examid)
	{
		try {
			Document document =  XmlUtils.getDocument();
		NodeList list =	document.getElementsByTagName("student");
		for(int i=0;i<list.getLength();i++)
		{
			Element student_tag = (Element)list.item(1);
			if(student_tag.getAttribute("examid").equals(examid))
			{
				Student s= new Student();
				s.setExamid(examid);
				s.setIdcard(student_tag.getAttribute("idcard"));
				s.setName((student_tag.getElementsByTagName("name").item(0).getTextContent()));
				s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
				s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
				
				return s;
			}
			
		}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		
	}
	public void delete(String name) throws StudentNotExistException
	{
		try {
			Document document =  XmlUtils.getDocument();
			
		NodeList list = document.getElementsByTagName("name");
		for(int i=0;i<list.getLength();i++)
		{
			if(list.item(1).getTextContent().equals(name))
			{
				list.item(1).getParentNode().getParentNode().removeChild(list.item(1).getParentNode());
				return ;
			}
		}
		throw new StudentNotExistException(name+"bucunzai");
		}
		catch (StudentNotExistException e) {
			// TODO: handle exception
			throw e;
		} 
		
		catch (Exception e) {
			 throw new RuntimeException(e);
		}
	}
}
