package cn.wolfcode.wms.web.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 0026.
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee currentEmp = UserContext.getCurrentEmp();
        if (currentEmp == null) {
            response.sendRedirect("/login.jsp");
            return false;
        }
        return true;
    }
}
