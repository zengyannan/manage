package com.md.manage.exception;


import com.md.manage.json.JsonResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    /**
     * 所有异常报错
     * @param request
     * @param  exception
     * @return
     * @throw Exception
     */

    @ExceptionHandler(Exception.class)
    public JsonResult allExceptionHandler(HttpServletRequest request, Exception exception)
            throws Exception{
        if(exception instanceof BaseException){
            return new JsonResult().failure(exception.getMessage(),((BaseException) exception).getCode(),((BaseException) exception).getErrorCode());
        }else {
            exception.printStackTrace();
            return new JsonResult().failure("网络异常",500,999);
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        Map<String,Object> map = new HashMap<>();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        return new JsonResult().failure(tips,500,999);
    }
}
