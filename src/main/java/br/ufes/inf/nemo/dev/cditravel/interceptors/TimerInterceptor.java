package br.ufes.inf.nemo.dev.cditravel.interceptors;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Dependent
@Timed
@Interceptor
public class TimerInterceptor implements Serializable {
  private static final Logger logger = Logger.getLogger(TimerInterceptor.class.getCanonicalName());

  @AroundInvoke
  public Object time(InvocationContext ctx) throws Exception {
    long start = System.currentTimeMillis();
    Object result = ctx.proceed();
    long time = System.currentTimeMillis() - start;
    logger.log(Level.INFO, "Time taken by method {0}: {1} ms",
        new Object[] {ctx.getMethod().getName(), time});
    return result;
  }
}
