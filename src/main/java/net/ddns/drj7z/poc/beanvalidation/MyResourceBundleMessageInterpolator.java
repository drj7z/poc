/**
 *
 */
package net.ddns.drj7z.poc.beanvalidation;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;


/**
 * Custom {@link ResourceBundleMessageInterpolator} that makes "easy" property
 * name printing with validation error message.
 *
 * We have not found any straightforward mean to print property name with
 * validation error message.
 *
 * Default validation error message is something like this:
 * <blockquote>may not be null</blockquote>
 * Ok, thanks but what may not be null?
 * I'd like something like this:
 * <blockquote>'Name' may not be null<br/>
 * where <code>Name</code> is the property that may not be null.</blockquote>
 *
 * After some analysis this is the solution we have worked out:
 * a customization of {@link ResourceBundleMessageInterpolator} that that can
 * manage some tags.
 *
 * A tag is in the form: <code>{#tag-name}</code>.<br/>
 * The valid tags are:
 * <ol>
 * <li><code>messageTemplate</code>: replaced bye default message
 * template<a href="#[1]">[1]</a>.</li>
 * </ol>
 *
 * <p>
 * Example:
 * <pre>
 * &commat;NotNull(message="'Firstname' {#messageTemplate}")
 * &commat;Length(min=2, max=128, message="'Firstname' {validation.Length}")
 * public String getFirstName ();
 * </pre>
 * In this way if <code>FirstName</code> is null than <i>'Firstname'</i>
 * followed by default message is printed; likely: <i>'Firstname' may not be
 * null</i>. In other words {#messageTemplate} is replaced with default
 * message template.
 * The definition of <code>&commat;Length</code> constraint is a bit
 * different. <code>{validation.Length}</code> is not a tag (<i>{</i> is not
 * followed by <i>#</i>) it is only a reference to another string and is
 * <b>not</b> an extension of this implementation but is already managed by
 * original implementation.
 * </p>
 *
 * <p>
 * <a name="[1]">[1]</a>&nbsp;Unfortunately after <code>message</code>
 * argument is defined in constraint definition default message template is
 * lost (<code>message</code> defines message template actually). That means
 * that it is not possible retrieve original message template. So what this
 * class does? It does a static guess on what original message template was.
 * This static guess is the following:
 * <blockquote>
 * {annotation-name.message}<br/>
 * where <code>annotation-name</code> if the fully qualified name of the
 * constraint annotation (example: <i>javax.validation.constraints.NotNull.
 * message</i>)
 * </blockquote>
 * </P>
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public final class MyResourceBundleMessageInterpolator extends
ResourceBundleMessageInterpolator {

  private static final Pattern msgTemplatePattern= Pattern.compile("\\{#messageTemplate\\}");

  /**
   *
   */
  public MyResourceBundleMessageInterpolator()
  {
    super();
  }

  /**
   * @param userResourceBundleLocator
   */
  public MyResourceBundleMessageInterpolator(
      ResourceBundleLocator userResourceBundleLocator)
  {
    super(userResourceBundleLocator);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public MyResourceBundleMessageInterpolator(ResourceBundleLocator arg0,
      boolean arg1)
  {
    super(arg0, arg1);
  }

  /* (non-Javadoc)
   * @see org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator#interpolate(java.lang.String, javax.validation.MessageInterpolator.Context, java.util.Locale)
   */
  @Override
  public String interpolate(String arg0, Context arg1, Locale arg2)
  {

    String messageTemplate= buildMessageTemplate(arg0,arg1);

    String message= super.interpolate(messageTemplate,arg1,arg2);

    // TODO I would like to get either property name or property path but
    // it seems it is not possible.

    return message;
  }

  /* (non-Javadoc)
   * @see org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator#interpolate(java.lang.String, javax.validation.MessageInterpolator.Context)
   */
  @Override
  public String interpolate(String arg0, Context arg1)
  {
    return interpolate(arg0,arg1,Locale.getDefault());
  }

  protected String buildMessageTemplate (String arg0, Context arg1)
  {
    Matcher msgTemplateMatcher= MyResourceBundleMessageInterpolator.
        msgTemplatePattern.matcher(arg0);

    return msgTemplateMatcher.replaceAll("{" +
        arg1.getConstraintDescriptor().getAnnotation().annotationType().
        getName() + ".message}");
  }

}

