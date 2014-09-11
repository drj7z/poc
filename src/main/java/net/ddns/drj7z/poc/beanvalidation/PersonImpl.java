/**
 *
 */
package net.ddns.drj7z.poc.beanvalidation;

/**
 * Person implementation.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class PersonImpl implements Person {

  private String firstName;
  private String lastName;

  /**
   * Do nothing.
   */
  public PersonImpl()
  { /* do nothing. */ }

  public PersonImpl(String firstName, String lastName)
  {
    this.firstName= firstName;
    this.lastName= lastName;
  }

  /**
   * @return the firstName
   */
  @Override
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  @Override
  public void setFirstName(String firstName)
  {
    this.firstName= firstName;
  }

  /**
   * @return the lastName
   */
  @Override
  public String getLastName()
  {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  @Override
  public void setLastName(String lastName)
  {
    this.lastName= lastName;
  }

}
