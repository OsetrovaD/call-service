package com.osetrova.callservice.validator;

import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface BaseValidator<T> extends Validator {

    @Override
    default boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(GenericTypeResolver.resolveTypeArgument(this.getClass(), BaseValidator.class));
    }

    @Override
    @SuppressWarnings("unchecked")
    default void validate(@Nullable Object o, Errors errors) {
        T object = (T) o;
        validateObject(object, errors);
    }

    void validateObject(T object, Errors errors);
}
