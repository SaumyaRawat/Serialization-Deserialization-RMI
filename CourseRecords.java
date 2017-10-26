/* [{​ ​"Name":​ ​"xyz",​ ​"CourseMarks":​ ​[{​ ​"CourseScore":​ ​85,​ ​"CourseName":​ ​"cs125"​ ​},​ ​{
"CourseScore":​ ​94,​ ​"CourseName":​ ​"cs210"​ ​}],​ ​"RollNo":​ ​202821785​ ​},​ ​{​ ​"Name":​ ​"pqr",
"CourseMarks":​ ​[{​ ​"CourseScore":​ ​20,​ ​"CourseName":​ ​"cs125"​ ​},​ ​{​ ​"CourseScore":​ ​37,
"CourseName":​ ​"cs210"​ ​}],​ ​"RollNo":​ ​202817265​ ​}​ ​]​ ​ */

import java.io.*;
import java.util.*;
import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.google.gson.Gson;  

public class CourseRecords{
	public static class Student{
		private String Name; 
	    List<courses> CourseMarks;  
	    private int RollNo;

	    public Student(final String Name,final List<courses> CourseMarks, final int RollNo) {
	        super();
	        this.Name = Name;
	        this.CourseMarks = CourseMarks;
	        this.RollNo = RollNo;
	    }
	      
	    public String getName(){ 
	        return Name; 
	    }  
	    public int getRollNo() { 
	        return RollNo; 
	    }
	}

    public static class courses{ 
      public int CourseScore; 
      public String CourseName; 
      
      public courses(final int CourseScore,final String CourseName) {
            super();
            this.CourseScore = CourseScore;
            this.CourseName = CourseName;
        }
   } 


   public static void main(String args[]) {
      String name = "xyz";
      int roll_no = 202821785;

      List<courses> CourseMarks = new ArrayList<>();  
      CourseMarks.add(new courses(85,"cs125"));  
      CourseMarks.add(new courses(94,"cs210"));        
      
      Student student = new Student(name,CourseMarks,roll_no);
      
      Gson gson = new Gson(); 
      String jsonString = gson.toJson(student); 
      System.out.println(jsonString);  
      
      /*student = gson.fromJson(jsonString, Student.class);  
      
      System.out.println("Roll No: "+ student.getRollNo()); 
      System.out.println("First Name: "+ student.getName().firstName); 
      System.out.println("Last Name: "+ student.getName().lastName);  
      String nameString = gson.toJson(name); 
      System.out.println(nameString);  
      
      name = gson.fromJson(nameString,Student.Name.class); 
      System.out.println(name.getClass()); 
      System.out.println("First Name: "+ name.firstName); 
      System.out.println("Last Name: "+ name.lastName); */
   }      
}  

/*

    public static class PersonSerializer implements JsonSerializer<Person> {
        public JsonElement serialize(final Person person, final Type type, final JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("id", new JsonPrimitive(person.getId()));
            result.add("name", new JsonPrimitive(person.getName()));
            Person parent = person.getParent();
            if (parent != null) {
                result.add("parent", new JsonPrimitive(parent.getId()));
            }
            return result;
        }
    }

    public static void main(final String[] args) {
        Person p = new Person(1, "Joe", new Person(2, "Mike"));
        com.google.gson.Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer())
                .create();
        System.out.println(gson.toJson(p));
    }
    */