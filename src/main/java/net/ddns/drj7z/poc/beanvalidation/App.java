package net.ddns.drj7z.poc.beanvalidation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Nothing interesting here.
 * You'd look at {@link Poc} and {@link MyResourceBundleMessageInterpolator}.
 *
 * @author drj7z <drjxzoidberg@gmail.com>
 * @version 1.0.0
 *
 */
public class App
{
  public void run ()
  {
    ApplicationContext context =
        new ClassPathXmlApplicationContext("application.xml");
    ((AbstractApplicationContext)context).registerShutdownHook();

    Poc poc= (Poc)context.getBean("poc");

    try {
      poc.run();
    } finally {
      // close the context.
      ((AbstractApplicationContext)context).close();
    }
  }



  public static void main( String[] args )
  {
    App app= new App();
    app.run();
  }

}
