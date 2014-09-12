# Bean validation and custom validation error messages

Bean validation is Java Specification Request [JSR]. JSR 349 more precisely. 
It is a specification allowing to validate bean properties and methods via 
annotation.
In this document we will see properties validation (annotating the getter) 
and how to customize validation error messages.

More information at:

* [Reference Implementation - Reference Guide](http://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html_single/)
* [JSR 349](https://jcp.org/en/jsr/detail?id=349)
* [Bean Validation](http://beanvalidation.org/)

## Very brief introduction

Bean validation allow you to define constraint on properties via annotating 
either them or getters. Moreover it is possible o validate methods (actual 
parameters and return value).

In this poc we will look at fields validation only. Methods validation will be 
explored in other poc, maybe.

The scope of this poc is to find out a(n easy) mean to print the invalid 
property name in the validation error message.

The poc uses [Spring framework](http://projects.spring.io/spring-framework/).

## Spring framework and bean validation

Spring Framework [SF] supports bean validation. You can refer to SF  
official documentation and [poc source code ](https://github.com/drj7z/poc/tree/beanvalidation) 
for more information.

In short:
define a `org.springframework.validation.beanvalidation.LocalValidatorFactoryBean`
bean -that detects a bean validation implementation (as hibernate validator 
is)- and inject a message interpolator bean (see [Interpolation](/#interpolation)).
Then validator can be inject to beans:

    <bean id="validator" depends-on="validatorFactory" 
          factory-bean="validatorFactory" factory-method="getValidator"/>

## Interpolation

Each constraint (annotation) has a _message template_.  
When a constraint is not satisfied than error message is interpolated.  
Made it easy interpolation is recursive resolution of parameters in message 
template.

## Interpolation customization

To customize the interpolation must be defined a class that inherits from 
`ResourceBundleMessageInterpolator`.

Very unfortunately we have not found any mean the property name reaches the
interpolate methods. Maybe that's a SF limit.  

Anyway the worked out solution is:

* interpolate methods recognize few tag;
  a tag is in the form `{#tag-name}`. 
* each defined constraints defines custom message template;
  `@NotNull(message="'Username' {#messageTemplate}")`.
* interpolate methods replace all tags with meaningful string. 
* then standard interpolation is applied.

Valid tags are:

* `messageTemplate`
  replaced by default constraint message template.
  *note*: that's is not fully true; [read here][Message Template The Truth] for details.

Just a note before finish.

In the example `@NotNull(message="'Username' {#messageTemplate}")` the word 
_Username_ is not localized.

To get it localized then the following message template can be defined: `@NotNull(message="'{strings.username}' {#messageTemplate}")`
and in the ValidationMessage__locale-code_.properties the property 
strings.username must be defined.

> Example:
>
> ValidationMessage_en.properties:
>
> > strings.username=Username
>
> ValidationMessage_de.properties:
>
> > string.username=Benutzername

## Conclusion

JSR349 allows for easy and straightforward bean validation.  
SF allow abstracting from JSR349 implementation by providing a generic 
interface.  
The only issue we have found is to get validated items drop in validation 
error message.  
The solution worked out in thi poc is a workaround but we don't like it very 
much.  
Maybe we will find a better solution.  
Stay tuned.



[Message Template The Truth]: http://drj7z.github.io/poc/beanvalidation/site/apidocs/net/ddns/drj7z/poc/beanvalidation/MyResourceBundleMessageInterpolator.html