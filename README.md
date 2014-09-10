# Gson and interface(s) (de-)serialization

Have you ever tried to (de-)serialize a java objects hierarchy referenced via interfaces?
If you tried than you know that while serialization is straightforward, de-serialiazation is a pain.
Here is presented the (a) solution I have worked out. 

## The easy part: serialization.
Suppose you have an interface `RegisterData` and a interface implementation `RegisterDataImpl`.

    public interface RegisterData {
      public String getUsername ();
      public void setusername (String name);
    }

    public class RegisterDataImpl implements RegisterData {
      private String username;

      // skipping trivial getter and setter implementation.

      
    }

Now imagine you are referecing such a implementation through the interface:

    public class ReferencesContainer {
      RegisterData registerData;

      public ReferencesContainer ()
      {
        registerData= new RegisterDataImpl();
        registerData.setUsername("name-user");
      }
    }

Now you want to serialize an instance of `ReferencesContainer` reference with Gson:

    ReferencesContainer referencesContainer= new ReferencesContainer();
    Gson gson= new GsonBuilder().create();
    gson.toJson(referencesContainer);

With that code you will get a well formatted json string:

    {"registerData":{"username":"name-user"}}

## The pain: de-serialization.

Now you want to de-serialize the json string into a `ReferencesContainer`:

    Gson gspon= new GsonBuilder().create();
    ReferencesContainer referencesContainer= (ReferencesContainer)gson.fromJson("{registerData:{username:name-user}}");

It won't work.

What you'll get is a RuntimeException:

    java.lang.RuntimeException: Unable to invoke no-args constructor for interface
      net.ddns.drj7z.poc.gson.RegisterData. Register an InstanceCreator with Gson for 
      this type may fix this problem.

That's no good at all!

The solution I worked out is not to register an `InstanceCreator` but is to register a `TypeAdapterFactory`.
The `TypeAdapterFactory` I implemented modify the serialization of objected referenced via interface in order to support the de-serialization. What it does is to serialize the instance as an json array with first element the class name of the intrface implmentation and second element the json representation of the instance.
The implementation is available here: [GsonTypeAdapterFactory.java](https://drive.google.com/file/d/0B3XS4gOyLMAiTUcwY2t3QjNRSzA/edit?usp=sharing)

    Gson gson= new GsonBuilder().registerTypeAdapterFactory(
        new GsonTypeAdapterFactory()).create();

    // serialize
    ReferencesContainer referencesContainerSource= new ReferencesContainer();
    String json= gson.toJson(referencesContainerSource);
    System.out.println("json: " + json);

    // de-serialize
    ReferencesContainer referencesContainer= gson.fromJson(json,ReferencesContainer.class);
    System.out.println(referencesContainer);

The output will be:

    json: {"registerData":["net.ddns.drj7z.poc.gson.RegisterDataImpl",{"username":"name-user"}]}
    username: name-user

As you can see, now the json string of `registerData` is an array and when it is deserialized first element of 
such a array is used to instantiate a concrete implementation that can hold it.
 
