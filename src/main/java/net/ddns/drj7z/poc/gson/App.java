package net.ddns.drj7z.poc.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * App.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class App
{
  /**
   * Will throw an exception on deserialization.
   */
  public void deserializeWithoutTypeAdapterFactory ()
  {
    Gson gson= new GsonBuilder().create();

    System.out.println("*without* type adapter factory.");

    System.out.println("serializing...");

    ReferencesContainer referencesContainerSource= new ReferencesContainer();

    String json= gson.toJson(referencesContainerSource);

    System.out.println("json: " + json);

    System.out.println("de-serializing...");

    ReferencesContainer referencesContainer= null;
    try {
      referencesContainer= gson.fromJson(json,ReferencesContainer.class);
    } catch ( Exception e ) { // will happen!
      System.err.println("I was sure that it's happen - an exception thrown while de-serializing: " + e.getMessage());
      return;
    }

    // de-serialization went fine!
    System.out.println(referencesContainer);

  }

  /**
   * Will get the expected object.
   */
  public void deserializeWithTypeAdapterFactory ()
  {
    Gson gson= new GsonBuilder().registerTypeAdapterFactory(
        new GsonTypeAdapterFactory()).create();

    System.out.println("*with* type adapter factory.");

    System.out.println("serializing...");

    ReferencesContainer referencesContainerSource= new ReferencesContainer();

    String json= gson.toJson(referencesContainerSource);

    System.out.println("json: " + json);

    System.out.println("de-serializing...");

    ReferencesContainer referencesContainer= null;
    try {
      referencesContainer= gson.fromJson(json,ReferencesContainer.class);
    } catch ( Exception e ) { // won't happen!
      System.err.println("If you are fallen here get ready to the end-of-world - an exception thrown while de-serializing: " + e.getMessage());
      return;
    }

    // de-serialization went fine!
    System.out.println(referencesContainer);

  }


  public void run ()
  {
    System.out.println("a bad case");
    deserializeWithoutTypeAdapterFactory();

    System.out.println("a good case");
    deserializeWithTypeAdapterFactory();
  }



  public static void main( String[] args )
  {
    App app= new App();
    app.run();
  }

}
