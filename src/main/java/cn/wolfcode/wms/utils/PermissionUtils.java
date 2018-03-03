package cn.wolfcode.wms.utils;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 0026.
 */
public class PermissionUtils {
    private PermissionUtils() {
    }

    public static String getExpression(Method method) {
        //获取到当前方法所在类的全限定名
        String className = method.getDeclaringClass().getName();
        //获取到方法名称
        String methodName = method.getName();
        return className + ":" + methodName;
    }
}
