package Q1;

import java.rmi.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.google.gson.Gson;  

public class Server_201401110
{
    public static void main(String args[])
    {
        try
        {
            Implementation_201401110 course_lookup=new  Implementation_201401110();
            Registry reg = LocateRegistry.createRegistry(5000);
            reg.bind("course_lookup", new  Implementation_201401110());
        }
        catch(Exception ex)
        {
            System.out.println ("Course lookup failed with error: " + ex);
        }
    }
}