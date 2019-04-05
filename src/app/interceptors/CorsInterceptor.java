package app.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 全局跨域拦截器
 */
public class CorsInterceptor implements Interceptor {
   @Override
   public void intercept(Invocation inv){
       inv.getController().getResponse().addHeader("Access-Control-Allow-Origin", "*");
       inv.invoke();
   }   
}
