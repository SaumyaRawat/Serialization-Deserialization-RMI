import java.rmi.*;
import java.util.Date;
import java.text.*;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.google.gson.Gson;  

public interface Interface_201401110 extends Remote
{
    public int deserialize_json(String content) throws RemoteException;
}

