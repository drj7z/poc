/**
 *
 */
package net.ddns.drj7z.poc.beanvalidation;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public interface Person {

  @NotNull(message="'Firstname' {#messageTemplate}")
  @Length(min=2, max=128, message="'Firstname' {validation.Length}")
  public String getFirstName ();
  public void setFirstName (String firstName);

  @NotNull(message="'Lastname' {#messageTemplate}")
  @Length(min=2, max=128, message="'Lastname' {validation.Length}")
  public String getLastName ();
  public void setLastName (String lastName);

}
