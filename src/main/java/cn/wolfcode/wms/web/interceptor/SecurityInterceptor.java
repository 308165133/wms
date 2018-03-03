package cn.wolfcode.wms.web.interceptor;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.utils.PermissionUtils;
import cn.wolfcode.wms.utils.RequiredPermission;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee currentEmp = UserContext.getCurrentEmp();
        //如果当前是超级管理员,放行
        if (currentEmp.isAdmin()) {
            return true;
        }
        //访问的方法上没有RequiredPermission注解,放行
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        if (!method.isAnnotationPresent(RequiredPermission.class)) {
            return true;
        }
        //如果当前用户有权限,放行
        String expression = PermissionUtils.getExpression(method);
        Set<String> expressions = UserContext.getExpressions();
        if (expressions.contains(expression)) {
            return true;
        }
        //没有权限跳转到一个提示页面中
//        request.getRequestDispatcher("/WEB-INF/views/nopermission.jsp").forward(request, response);
//        return false;
        throw new cn.wolfcode.wms.utils.SecurityException();
    }
}
