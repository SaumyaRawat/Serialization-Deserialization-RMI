import java.io.*;
import java.rmi.*;
import java.net.*;
import java.util.Scanner;
import java.util.Date;
import java.text.*;
import java.util.*;
import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.google.gson.Gson;  


public class Client_201401110
{
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
    }

    public static class courses implements java.io.Serializable{
        public int CourseScore; 
        public String CourseName; 
        public courses(final int CourseScore,final String CourseName) {
            super();
            this.CourseScore = CourseScore;
            this.CourseName = CourseName;
        }
    }


    public static String serialize_json(String content)  throws RemoteException {
        List<Student> students = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(content);
        int count, flag = 0, done = 0;
        String name="";
        String roll_no="";        
        List<courses> CourseMarks = new ArrayList<>();  
        // for each line in input.txt
        while (st.hasMoreTokens()) {
            CourseMarks = new ArrayList<>();
            String line_one = st.nextToken(";");
            StringTokenizer st1 = new StringTokenizer(line_one);
            // for each entry in each line (3 entries in total)
            flag = 0;
            name = "";
            roll_no = "";
            while(st1.hasMoreTokens()){

                String entry = st1.nextToken(":");
                    // for each course
                    StringTokenizer st2 = new StringTokenizer(entry);
                    String coursename = "";
                    while(st2.hasMoreTokens()){
                        int marks = 0;
                        String course_string = st2.nextToken(",");
                        if(flag==0) {
                            if(course_string.matches("[A-Z]+")) {
                                name = course_string;
                            }
                            else if(course_string.matches("[0-9]+")) {
                                roll_no = course_string;
                                flag = 1;
                            }
                        }
                        else if(flag==1){
                             if(course_string.matches("[0-9]+")){
                                    marks = Integer.parseInt(course_string);
                                    CourseMarks.add(new courses(marks,coursename));  
                             }
                             else {
                                coursename = course_string;
                            }
                        }
                    }
                }
            students.add(new Student(name,CourseMarks,roll_no));
        }
        Gson gson = new Gson(); 
        String jsonString = gson.toJson(students); 
        return(jsonString);
    }

    public static void main(String args[])
    {
        String serialized_content="";

        try
            {
                while(true)
                {
                    String url="rmi://localhost:5000/course_lookup";
                    String content = "";
                    int choice, res;
                    Interface_201401110 intf=(Interface_201401110)Naming.lookup(url);
                    try{
                      		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
                      		content = "";
							while(true){
								String line = br.readLine();
								if(line == null) break;
									content += (line +";");
							}
					}
					
					catch (FileNotFoundException e){
					    e.printStackTrace();
					}
					
					catch (IOException e){
					   	e.printStackTrace();
					}

                    System.out.println("Enter 1 for json and 2 for protobuf: ");
                    Scanner s=new Scanner(System.in);
                   	choice=Integer.parseInt(s.next());
                    switch(choice){
                        case 1:
                            serialized_content = serialize_json(content);
                            //System.out.println(serialized_content);
                            res = intf.deserialize_json(serialized_content);
							break;

                        case 2:
                            break;

                        default:
                            System.out.println("Entered option does not exist!");
                            break;
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error: "+ex);
            }
    }
}