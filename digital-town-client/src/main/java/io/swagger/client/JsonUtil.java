/**
 * DigitalTown SSO API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Date;
import io.swagger.client.model.*;

public class JsonUtil {
  public static GsonBuilder gsonBuilder;

  static {
    gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
      }
    });
  }

  public static Gson getGson() {
    return gsonBuilder.create();
  }

  public static String serialize(Object obj){
    return getGson().toJson(obj);
  }

  public static <T> T deserializeToList(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
  }

  public static <T> T deserializeToObject(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
  }

  public static Type getListTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("ClientCreate".equalsIgnoreCase(className)) {
      return new TypeToken<List<ClientCreate>>(){}.getType();
    }
    
    if ("ClientCreate1".equalsIgnoreCase(className)) {
      return new TypeToken<List<ClientCreate1>>(){}.getType();
    }
    
    if ("ClientUpdate".equalsIgnoreCase(className)) {
      return new TypeToken<List<ClientUpdate>>(){}.getType();
    }
    
    if ("ClientUpdate1".equalsIgnoreCase(className)) {
      return new TypeToken<List<ClientUpdate1>>(){}.getType();
    }
    
    if ("Token".equalsIgnoreCase(className)) {
      return new TypeToken<List<Token>>(){}.getType();
    }
    
    if ("Token1".equalsIgnoreCase(className)) {
      return new TypeToken<List<Token1>>(){}.getType();
    }
    
    if ("TokenRefresh".equalsIgnoreCase(className)) {
      return new TypeToken<List<TokenRefresh>>(){}.getType();
    }
    
    if ("TokenRefresh1".equalsIgnoreCase(className)) {
      return new TypeToken<List<TokenRefresh1>>(){}.getType();
    }
    
    if ("UserCreate".equalsIgnoreCase(className)) {
      return new TypeToken<List<UserCreate>>(){}.getType();
    }
    
    if ("UserCreate1".equalsIgnoreCase(className)) {
      return new TypeToken<List<UserCreate1>>(){}.getType();
    }
    
    if ("Users".equalsIgnoreCase(className)) {
      return new TypeToken<List<Users>>(){}.getType();
    }
    
    if ("UsersEdit".equalsIgnoreCase(className)) {
      return new TypeToken<List<UsersEdit>>(){}.getType();
    }
    
    return new TypeToken<List<Object>>(){}.getType();
  }

  public static Type getTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("ClientCreate".equalsIgnoreCase(className)) {
      return new TypeToken<ClientCreate>(){}.getType();
    }
    
    if ("ClientCreate1".equalsIgnoreCase(className)) {
      return new TypeToken<ClientCreate1>(){}.getType();
    }
    
    if ("ClientUpdate".equalsIgnoreCase(className)) {
      return new TypeToken<ClientUpdate>(){}.getType();
    }
    
    if ("ClientUpdate1".equalsIgnoreCase(className)) {
      return new TypeToken<ClientUpdate1>(){}.getType();
    }
    
    if ("Token".equalsIgnoreCase(className)) {
      return new TypeToken<Token>(){}.getType();
    }
    
    if ("Token1".equalsIgnoreCase(className)) {
      return new TypeToken<Token1>(){}.getType();
    }
    
    if ("TokenRefresh".equalsIgnoreCase(className)) {
      return new TypeToken<TokenRefresh>(){}.getType();
    }
    
    if ("TokenRefresh1".equalsIgnoreCase(className)) {
      return new TypeToken<TokenRefresh1>(){}.getType();
    }
    
    if ("UserCreate".equalsIgnoreCase(className)) {
      return new TypeToken<UserCreate>(){}.getType();
    }
    
    if ("UserCreate1".equalsIgnoreCase(className)) {
      return new TypeToken<UserCreate1>(){}.getType();
    }
    
    if ("Users".equalsIgnoreCase(className)) {
      return new TypeToken<Users>(){}.getType();
    }
    
    if ("UsersEdit".equalsIgnoreCase(className)) {
      return new TypeToken<UsersEdit>(){}.getType();
    }
    
    return new TypeToken<Object>(){}.getType();
  }

};
