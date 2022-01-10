package me.soungho.openmonitoring.utils.validation.annotation;

import me.soungho.openmonitoring.utils.validation.validator.HttpEndpointValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.FIELD)            //필드에 붙을 수 있다는 의미
@Target(ElementType.PARAMETER)          //파라미터에 붙을 수 있다는 의미
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HttpEndpointValidator.class)
public @interface HTTPEndpoint {
    String message() default "HTTP 엔드포인트";
    Class[] groups() default {};
    Class[] payload() default {};
}