/**
 *
 */
package net.ddns.drj7z.poc.gson;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Interface type adapter for Gson.
 * Allows to serialize and deserialize a field which "type" is an interface.
 * When serialized the field is written as an array which members:
 * [0] is the actual class name of the instance concrete type.
 * [1] is the json representation of the instance.
 * When deserialized an instance of concrete type is created -reading its
 * name from element [0]- and then it is filled with values from element [1].
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class GsonTypeAdapterFactory implements TypeAdapterFactory {

  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken)
  {
    Class<?> rawType= typeToken.getRawType();
    if ( rawType.isInterface() ) {
      return createPacketAdapter(gson,typeToken);
    }

    // else: not managing this type.
    return null;
  }

  private <T> TypeAdapter<T> createPacketAdapter (final Gson gson,
      final TypeToken<T> typeToken) {

    return new TypeAdapter<T>() {

      @Override
      public void write (JsonWriter out, T value) throws IOException {
        if (value == null) {
          out.nullValue();
          return;
        }

        try {
          out.beginArray();
          out.value(value.getClass().getName());
          gson.toJson(value,value.getClass(),out);
          out.endArray();
        } catch ( IOException e ) {
          throw new IOException("cannot serialize " +
              "interface '" + typeToken.getRawType().getName() + "' which " +
              "concrete calss is '" + value.getClass().getName() + "'",e);
        }
      }

      @Override
      public T read (JsonReader in) throws IOException {

        if (in.peek() == JsonToken.NULL) {
          in.nextNull();
          return null;
        }

        try {
          in.beginArray();
          String className= in.nextString();
          @SuppressWarnings("unchecked")
          T instance= (T)gson.fromJson(in,Class.forName(className));
          in.endArray();

          return instance;
        } catch ( IOException e ) {
          throw new IOException(
              "cannot deserialize interface from '" + in.getPath() + ":" + in + "'",e);
        } catch ( ClassNotFoundException e ) {
          throw new IOException("cannot deserialize " +
              "interface '" + typeToken.getRawType().getName() +
              "' as connot find concrete class: " + e.getMessage(),e);
        }
      }

    }; // refers to return new... this is why semicoon (;) is needed.
  }

}
