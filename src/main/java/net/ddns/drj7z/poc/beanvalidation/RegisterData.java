package net.ddns.drj7z.poc.beanvalidation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public interface RegisterData {

  /**
   * @return the username
   */
  @NotNull(message="'Username' {#messageTemplate}")
  @Length(min=3, max=16, message="'Username' {validation.Length}")
  public String getUsername();

  /**
   * @param username the username to set
   */
  public void setUsername(String username);

  /**
   * @return the password
   */
  @NotNull(message="'Password' {#messageTemplate}")
  @Length(min=8, max=16, message="'Password' {validation.Length}")
  public String getPassword();

  /**
   * @param password the password to set
   */
  public void setPassword(String password);

  /**
   * @return the person
   */
  @Valid
  public Person getPerson();

  /**
   * @param person the person to set
   */
  public void setPerson(Person person);

  /**
   * @return the email
   */
  @Email(message="'Email' {#messageTemplate}")
  public String getEmail();

  /**
   * @param email the email to set
   */
  public void setEmail(String email);

}