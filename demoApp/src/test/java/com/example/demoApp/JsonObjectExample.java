package com.example.demoApp;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonObjectExample {

  @Test
  void jsonObjectCanBeMapped(){
      final JsonObject jsonObject = new JsonObject();
      jsonObject.put("id",123);
      jsonObject.put("name", "Alice");
      jsonObject.put("loves_vertx", true);
      final String encoded = jsonObject.encode();
      assertEquals("{\"id\":123,\"name\":\"Alice\",\"loves_vertx\":true}",encoded);

      final JsonObject decodedJsonObject = new JsonObject(encoded);
      assertEquals(jsonObject,decodedJsonObject);

  }

  @Test
  void jsonObjectCanBeCreatedFromMap(){
    final Map<String,Object> myMap = new HashMap<>();
    myMap.put("id",123);
    myMap.put("name", "Alice");
    myMap.put("loves_vertx", true);

    final JsonObject asJsonObject = new JsonObject(myMap);
    assertEquals(myMap,asJsonObject.getMap());
    assertEquals(123,asJsonObject.getInteger("id"));
    assertEquals("Alice",asJsonObject.getString("name"));
    assertEquals(true,asJsonObject.getBoolean("loves_vertx"));
  }

  @Test
  void jsonArrayCanBeMapped(){
    final JsonArray myJsonArray = new JsonArray();
    myJsonArray
      .add(new JsonObject().put("id",1))
      .add(new JsonObject().put("id",2))
      .add(new JsonObject().put("id",3))
      ;

    assertEquals("[{\"id\":1},{\"id\":2},{\"id\":3}]",myJsonArray.encode());
    //assertEquals("[{\"id\":1},{\"id\":2},{\"id\":3},\"randomValue\"]",myJsonArray.encode());

  }
}
