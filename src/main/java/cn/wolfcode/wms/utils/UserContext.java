package cn.wolfcode.wms.utils;

import cn.wolfcode.wms.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by Administrator on 0026.
 */
public class UserContext {
    public static final String EMPLOYEE_IN_SESSION = "employee_in_session";
    public static final String EXPRESSIONS_IN_SESSION = "expressions_in_session";

    public static HttpSession getSession() {
        return
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    public static void setCurrentEmp(Employee currentEmp) {
        if (currentEmp == null) {
            getSession().invalidate();
        } else {
            getSession().setAttribute(EMPLOYEE_IN_SESSION, currentEmp);
        }
    }

    public static Employee getCurrentEmp() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static void setExpressions(Set<String> expressions) {
        getSession().setAttribute(EXPRESSIONS_IN_SESSION, expressions);
    }

    public static Set<String> getExpressions() {
        return (Set<String>) getSession().getAttribute(EXPRESSIONS_IN_SESSION);
    }
}
