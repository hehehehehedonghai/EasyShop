package com.shop.aop;

import com.shop.common.Const;
import com.shop.common.ResponseCode;
import com.shop.common.ServerResponse;
import com.shop.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;


/**
 * ManagementAop
 * 对后台商品管理controller进行AOP
 * @author Yarn
 * @create 2017/11/14/8:38
 */
@Aspect
@Component
public class ManagementAop {

   /* @Before("execution(* com.shop.controller.backend..*.*(..))")
    public ServerResponse before(JoinPoint point) throws Throwable {
        //拿到controller方法中的参数个数
        Object[] args = point.getArgs();
        System.out.println(args.toString());
        //检查是否为空
        if (args != null) {
            //循环便利
            for (Object arg : args) {
                //拿到session
                System.out.println(arg.getClass());
                if (arg instanceof HttpSession) {
                    //强转为session
                    HttpSession session =  (HttpSession) arg;
                    //获取当前登录对象
                    User user = (User) session.getAttribute(Const.CURRENT_USER);
                    //对象不为空
                    if (user != null) {
                        //对登录的对象进行角色判断
                        if (user.getRole().intValue() == Const.Role.ROLE_ADMIN){
                            //是管理员
                            return ServerResponse.createBySuccess();
                        } else {
                            //不是管理员
                            throw new RuntimeException("没有权限");
                        }
                    } else {
                        //用户未登录
                        throw new RuntimeException("用户未登录");
                    }
                }
            }
        }
        throw new RuntimeException("用户未登录");
    }*/
}
