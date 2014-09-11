/**
 *
 */
package net.ddns.drj7z.poc.beanvalidation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

/**
 *  A proof-of-concept for show how to validate a bean using Spring and
 *  Bean Validation 1.1 specification.
 *
 *  Validated bean is {@link RegisterDataImpl} implementing
 *  {@link RegisterData}.
 *
 *  Class's {@link Poc#run()} method:
 *  <oL>
 *  <li>creates a RegisterDataImpl object;</li>
 *  <li>fills some object's fields;</li>
 *  <li>validates it (via inject (by spring) validator;</li>
 *  <li>prints all validation error.</li>
 *  </ol>
 *
 *  Fields values can be customized via
 *  <code>net.ddns.drj7z.poc.beanvalidation.messages</code>
 *
 *  This poc use a customized {@link ResourceBundleMessageInterpolator}
 *  ({@link MyResourceBundleMessageInterpolator}) in orde to allow to easily
 *  print property name together with error message.
 *  See {@link MyResourceBundleMessageInterpolator} for more details.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class Poc {

  private Validator validator;



  /**
   * Do nothing.
   */
  public Poc ()
  { /* do nothing */ }



  public void run ()
  {
    RegisterData rd= new RegisterDataImpl();

    rd.setEmail(Messages.getString("register-data.email")); //$NON-NLS-1$
    rd.setUsername(Messages.getString("register-data.username")); //$NON-NLS-1$
    rd.setPassword(Messages.getString("register-data.password")); //$NON-NLS-1$
    rd.setPerson(new PersonImpl(Messages.getString("register-data.person." +
        "firstName"),Messages.getString("register-data.person.lastName"))); //$NON-NLS-1$
    ((RegisterDataImpl)rd).setId(Long.valueOf(Messages.getString("register-data.id"))); //$NON-NLS-1$

    Set<ConstraintViolation<RegisterData>> cviols= getValidator().validate(rd);
    for ( ConstraintViolation<RegisterData> cviol: cviols ) {
      System.err.println("validation error: " + cviol.getMessage()); //$NON-NLS-1$
    }
  }


  /**
   * @return the validator
   */
  protected Validator getValidator()
  {
    return validator;
  }

  /**
   * @param validator the validator to set
   */
  public void setValidator(Validator validator)
  {
    this.validator= validator;
  }

}
