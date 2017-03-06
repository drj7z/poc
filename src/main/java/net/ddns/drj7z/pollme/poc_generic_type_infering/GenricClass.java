package net.ddns.drj7z.pollme.poc_generic_type_infering;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenricClass<T> {

  private Class<T> typeClass;

  private class TypeInfer extends GenricClass<T> {}


  public GenricClass()
  {
    typeClass= (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];

    int i;
    i= 0;
  }

  public Type getActualType ()
  {
    TypeInfer typeInfer= new TypeInfer();
    Type type= typeInfer.getClass().getGenericSuperclass();

    if ( type instanceof ParameterizedType ) {
      Type[] types= ((ParameterizedType)type).getActualTypeArguments();
      if ( types.length == 0 ) {
        return null;
      }
      else {
        return types[0];
      }
    }
    else {
      return null;
    }
  }

}
