# Bean validation and custom validation error messages

Bean validation is Java Specification Request [JSR]. The JSR 349 more precisely. 
It is a specification allowing to validate bean fields and methods via annotation.
In this document specifically we will see fields validation (annotating the getter) 
and how to customize validation error messages.
[Spring Framework](http://projects.spring.io/spring-framework/) will be used as a support.

More information at:
* [Reference Implementation - Reference Guide](http://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html_single/)
* [JSR 349](https://jcp.org/en/jsr/detail?id=349)
* [Bean Validation](http://beanvalidation.org/)


## Very brief introduction

Bean validation allow you to define constraint on fields via annotating either 
them or getters. Moreover it is possible o validate methods (actual parameters 
and return value).

In this poc we will look at fields validation only. Methods validation will be 
explored in other poc, maybe.

The scope of this poc is to find out a(n easy) mean to print the invalid 
property name in the validation error message.

The poc is supported by [http://projects.spring.io/spring-framework/](Spring 
framework).

## Spring framework and bean validation

Spring Framework [SF] supports bean validation. You can refer to the 
official documentation and poc TODO[link](source code form more information).

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
interpolate methods.  
Maybe that's a limit of Spring.  
Anyway the worked out solution is:
* interpolate methods recognize few tag;
  a tag is in the form `{#tag-name}`. 
* each defined constraints defines custom message template;
  `@NotNull(message="'Username' {#messageTemplate}")`.
* interpolate methods replace all tags with meaningful string. 
* then standard interpolation is applied.

Valid tags are:
* `messageTemplate`
  replace by default constraint message template.

Just a note before finish.

In the example `@NotNull(message="'Username' {#messageTemplate}")` the word 
_Username_ is not localized.
To get it localized then the following message template can be defined: `@NotNull(message="'{strings.username}' {#messageTemplate}")`
and in the ValidationMessage__locale-code_.properties the property 
strings.username must be defined.
> Example:
> ValidationMessage_en.properties:
> > strings.username=Username
> ValidationMessage_de.properties:
> > string.username=Benutzername
