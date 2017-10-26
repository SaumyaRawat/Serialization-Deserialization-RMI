import java.io.*;
import java.rmi.*;
import java.net.*;
import java.util.Scanner;
import java.util.Date;
import java.text.*;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.google.gson.Gson;  

public class Client_201401110
{
    public static void main(String args[])
    {
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
                            res = intf.serialize_json(content);
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