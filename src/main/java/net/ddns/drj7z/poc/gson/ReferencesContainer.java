/**
 *
 */
package net.ddns.drj7z.poc.gson;


/**
 * Very stupid class that has a field referenced via interface.
 * It is the base for gson de-serialization demonstration.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class ReferencesContainer {

  RegisterData registerData;

  /**
   *
   */
  public ReferencesContainer()
  {
    registerData= new RegisterDataImpl();
    registerData.setUsername("name-user");
  }

  @Override
  public String toString ()
  {
    return "username: " + registerData.getUsername();
  }

}
