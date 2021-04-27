package br.ufes.inf.nemo.dev.cditravel.interceptors;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Timed {
}
