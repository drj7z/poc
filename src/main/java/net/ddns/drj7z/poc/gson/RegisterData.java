package net.ddns.drj7z.poc.gson;


/**
 * Register data interface.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public interface RegisterData {

  /**
   * @return the username
   */
  public String getUsername();

  /**
   * @param username the username to set
   */
  public void setUsername(String username);

  /**
   * @return the password
   */
  public String getPassword();

  /**
   * @param password the password to set
   */
  public void setPassword(String password);

  /**
   * @return the name
   */
  public String getName();

  /**
   * @param name the name to set
   */
  public void setName(String name);

  /**
   * @return the email
   */
  public String getEmail();

  /**
   * @param email the email to set
   */
  public void setEmail(String email);

}