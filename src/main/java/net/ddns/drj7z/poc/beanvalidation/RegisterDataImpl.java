/**
 *
 */
package net.ddns.drj7z.poc.beanvalidation;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;



/**
 * Register data implementation.
 *
 * Add a field: <code>id</code>.
 * Add a constraint on <code>password</code>.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class RegisterDataImpl implements RegisterData {

  private Long id;
  private String username;
  private String password;
  private Person person;
  private String email;




  /**
   *
   */
  public RegisterDataImpl()
  {}



  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getUsername()
   */
  @Override
  public String getUsername()
  {
    return username;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setUsername(java.lang.String)
   */
  @Override
  public void setUsername(String username)
  {
    this.username= username;
  }


  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getPassword()
   */
  @Override
  @Length(min=4, max=20, message="'Password' {#messageTemplate}")
  public String getPassword()
  {
    return password;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setPassword(java.lang.String)
   */
  @Override
  public void setPassword(String password)
  {
    this.password= password;
  }


  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getName()
   */
  @Override
  public Person getPerson()
  {
    return person;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setName(java.lang.String)
   */
  @Override
  public void setPerson(Person person)
  {
    this.person= person;
  }


  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#getEmail()
   */
  @Override
  public String getEmail()
  {
    return email;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.poc.beanvalidation.RegisterData#setEmail(java.lang.String)
   */
  @Override
  public void setEmail(String email)
  {
    this.email= email;
  }


  @Min(value=1025, message="'Id' {#messageTemplate}")
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id= id;
  }

}
