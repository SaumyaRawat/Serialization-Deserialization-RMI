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
import src.ResultProto.Result;
import src.ResultProto.Student;
import src.ResultProto.CourseMarks;


public class Implementation_201401110 extends UnicastRemoteObject implements Interface_201401110
{
    PrintWriter json_writer;
    PrintWriter protobuf_writer;

	public static class Student_class  implements java.io.Serializable{
		private String Name; 
		List<courses> Coursemarks;  
		private String RollNo;

		public Student_class(final String Name,final List<courses> Coursemarks, final String RollNo){
			super();
			this.Name = Name;
			this.Coursemarks = Coursemarks;
			this.RollNo = RollNo;
		}

		public String getName(){ 
			return Name; 
		}  
		public String getRollNo() { 
			return RollNo; 
		}
        public List<courses> getCoursemarks()
        {
            return Coursemarks;
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
        public int getCoursemarks(){
            return CourseScore;
        }
    }

    public  Implementation_201401110() throws RemoteException{
        //this.students = new ArrayList<>();
        try {
            this.json_writer = new PrintWriter("json_output.txt", "UTF-8");
            this.protobuf_writer = new PrintWriter("protobuf_output.txt", "UTF-8");
        } catch(Exception e) {}
    }

    public int deserialize_json(String serialized_content)  throws RemoteException{
        Gson gson = new Gson();        
        Student_class[] students = gson.fromJson(serialized_content, Student_class[].class);
        for(Student_class s: students){
            List<courses> courses = s.getCoursemarks();
            String line = s.getName()+","+s.getRollNo()+":";
            int start = 0;
            System.out.print(line);
            this.json_writer.print(line);
            for( courses c: courses){
                if(start==0){
                    line = c.getCourseName()+","+c.getCoursemarks();
                    start = 1;
                }
                else {
                    line = ":"+c.getCourseName()+","+c.getCoursemarks();
                }
                System.out.print(line);
                this.json_writer.print(line);                
            }
            this.json_writer.print("\n");
        } 

        //System.out.println(studentContainer.toString());
        this.json_writer.close();
        return(1);

    }

        public int deserialize_protobuf(byte[] serialized_content_protobuf)  throws RemoteException{
            try {
                Result R = Result.parseFrom(serialized_content_protobuf);
                List<Student> S = R.getStudentList();
                for(Student s: S){
                    String line = s.getName()+","+s.getRollNum()+":";
                    int start = 0;
                    System.out.print(line);
                    this.protobuf_writer.print(line);
                    List<CourseMarks> courses = s.getMarksList();
                    for(CourseMarks c:courses){
                        if(start==0){
                            line = c.getName()+","+c.getScore();
                            start = 1;
                        }
                        else {
                            line = ":"+c.getName()+","+c.getScore();
                        }
                        //System.out.print(line);
                        this.protobuf_writer.print(line);                
                    }
                this.protobuf_writer.print("\n");
                } 
                //System.out.println(studentContainer.toString());
                this.protobuf_writer.close();
            } catch( Exception e){}
            return(1);
        }
}


