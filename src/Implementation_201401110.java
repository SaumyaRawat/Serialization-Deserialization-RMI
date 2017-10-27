package src;

import java.rmi.*;
import java.rmi.server.*;

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

public class Implementation_201401110 extends UnicastRemoteObject implements Interface_201401110
{
    PrintWriter writer;

	public static class Student  implements java.io.Serializable{
		private String Name; 
		List<courses> CourseMarks;  
		private String RollNo;

		public Student(final String Name,final List<courses> CourseMarks, final String RollNo){
			super();
			this.Name = Name;
			this.CourseMarks = CourseMarks;
			this.RollNo = RollNo;
		}

		public String getName(){ 
			return Name; 
		}  
		public String getRollNo() { 
			return RollNo; 
		}
        public List<courses> getCourseMarks()
        {
            return CourseMarks;
        }

	}

	public static class courses implements java.io.Serializable{
        public int CourseScore; 
        public String CourseName; 
        public courses(final int CourseScore,final String CourseName) {
            super();
            this.CourseScore = CourseScore;
            this.CourseName = CourseName;
        }
        public String getCourseName(){
            return CourseName;
        }
        public int getCourseMarks(){
            return CourseScore;
        }
    }

    public  Implementation_201401110() throws RemoteException{
        //this.students = new ArrayList<>();
        try {
            this.writer = new PrintWriter("json_output.txt", "UTF-8");
        } catch(Exception e) {}
    }

    public int deserialize_json(String serialized_content)  throws RemoteException{
        Gson gson = new Gson();        
        Student[] students = gson.fromJson(serialized_content, Student[].class);
        for(Student s: students){
            List<courses> courses = s.getCourseMarks();
            String line = s.getName()+","+s.getRollNo()+":";
            int start = 0;
            System.out.print(line);
            this.writer.print(line);
            for( courses c: courses){
                if(start==0){
                    line = c.getCourseName()+","+c.getCourseMarks();
                    start = 1;
                }
                else {
                    line = ":"+c.getCourseName()+","+c.getCourseMarks();
                }
                System.out.print(line);
                this.writer.print(line);                
            }
            this.writer.print("\n");
        } 

        //System.out.println(studentContainer.toString());
        this.writer.close();
        return(1);

    }
}


