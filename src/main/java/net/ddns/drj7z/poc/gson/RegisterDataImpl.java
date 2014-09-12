/**
 *
 */
package net.ddns.drj7z.poc.gson;



/**
 * Register data implementation.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class RegisterDataImpl implements RegisterData {

  private String username;
  private String password;
  private String name;
  private String email;




  /**
   *
   */
  public RegisterDataImpl()
  {}



  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getUsername()
   */
  public String getUsername()
  {
    return username;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setUsername(java.lang.String)
   */
  public void setUsername(String username)
  {
    this.username= username;
  }


  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getPassword()
   */
  public String getPassword()
  {
    return password;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setPassword(java.lang.String)
   */
  public void setPassword(String password)
  {
    this.password= password;
  }


  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getName()
   */
  public String getName()
  {
    return name;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setName(java.lang.String)
   */
  public void setName(String name)
  {
    this.name= name;
  }


  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getEmail()
   */
  public String getEmail()
  {
    return email;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setEmail(java.lang.String)
   */
  public void setEmail(String email)
  {
    this.email= email;
  }

}
