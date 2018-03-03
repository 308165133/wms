package cn.wolfcode.wms.utils;

//自定义一个业务逻辑异常
public class LogicException extends RuntimeException {
    public LogicException() {
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
