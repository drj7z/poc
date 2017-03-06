/**
 *
 */
package net.ddns.drj7z.poc.uuid;

import java.util.UUID;

/**
 * @author emaschietto
 *
 */
public class App {

  private String username= null; // unique

  /**
   * Do nothing.
   */
  public App () { /* do nothing. */ }

  public App (String username)
  {
    this.username= username;
  }

  @Override
  public String toString ()
  {
    if ( username == null ) {
      return "";
    }
    // else...
    return username;
  }

  private static void printUUID (UUID uuid, String... strings)
  {
    for ( String string: strings ) {
      System.out.print(string + " : ");
    }

    System.out.print(uuid.variant() + " : " + uuid.version() + " : " +
        uuid.toString() + " : " +
        String.valueOf(uuid.getMostSignificantBits()) + " : " +
        String.valueOf(uuid.getLeastSignificantBits()));

    if ( uuid.variant() == 1) { // time-based
      System.out.print(" : " + uuid.clockSequence() + " : " + uuid.timestamp() + " : " + uuid.node());
    }

    System.out.println();
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    UUID uuid;

    uuid= UUID.randomUUID();
    App.printUUID(uuid,"This is a random uuid");

    uuid= UUID.fromString(uuid.toString());
    App.printUUID(uuid,"This is a from string of previous uuid.toString");

    App app= new App("drj7z");
    uuid= UUID.nameUUIDFromBytes(app.toString().getBytes());
    App.printUUID(uuid,"This is a name uuid from bytes starting from " + app);

    uuid= new UUID(uuid.getMostSignificantBits(),uuid.getLeastSignificantBits());
    App.printUUID(uuid,"This is a new UUID from most-sig and least-sig of previous uuid");

    app= new App("drj7z");
    uuid= UUID.nameUUIDFromBytes(app.toString().getBytes());
    App.printUUID(uuid,"This is a name uuid from bytes starting again from " + app);

  }

}
