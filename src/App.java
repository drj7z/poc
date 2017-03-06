import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class App {

  public App()
  {
    // TODO Auto-generated constructor stub
  }

  public static void main(String[] args)
  {
    String x= "ciao ${io} e {ancheTe} come {vorrei} ${staAndando}?";

    String ps= "(((" + Pattern.quote("{") + ")(.*?)(" + Pattern.quote("}") + "))|((" + Pattern.quote("${") + ")(.*?)(" + Pattern.quote("}") + ")))";
    Pattern tagPattern= Pattern.compile(ps);
    Matcher tagMatcher= tagPattern.matcher(x);
    while ( tagMatcher.find() ) {
      int i= tagMatcher.groupCount();
      String y= tagMatcher.group();
      String zero= tagMatcher.group(0);
      String uno= tagMatcher.group(1);
      String due= tagMatcher.group(2);
      String tre= tagMatcher.group(3);
      String quattro= tagMatcher.group(4);
      String cinque= tagMatcher.group(5);
      String sei= tagMatcher.group(6);
      String sette= tagMatcher.group(7);
      String otto= tagMatcher.group(8);
      System.out.println(y);
    }

    String open;
    String close;

    open= "${#tag-open".replaceAll("([{}$.?])","\\\\$1");
    close= "#tag-close}".replaceAll("([{}$.?])","\\\\$1");

    System.out.println("open: " + open);
    System.out.println("close: " + close);

    open= java.util.regex.Pattern.compile("([{}$.?])").matcher("${#tag-open").replaceAll("\\\\$1");
    close= java.util.regex.Pattern.compile("([{}$.?])").matcher("#tag-close}").replaceAll("\\\\$1");

    System.out.println("open: " + open);
    System.out.println("close: " + close);

    System.out.println(Pattern.quote("${.*?}"));

  }

}
