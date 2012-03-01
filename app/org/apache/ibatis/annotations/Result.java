package org.apache.ibatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Result {
  public abstract boolean id() default false;

  public abstract String column() default "";

  public abstract String property() default "";

  public abstract Class<?> javaType() default void.class;

  public abstract JdbcType jdbcType() default JdbcType.UNDEFINED;

  public abstract Class<? extends TypeHandler> typeHandler() default TypeHandler.class;

  public abstract One one() default @One;

  public abstract Many many() default @Many;
}
