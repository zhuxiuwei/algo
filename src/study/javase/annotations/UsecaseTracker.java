package study.javase.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 利用注解处理器，来解析处理注解
 * 用到了反射机制。
 */
public class UsecaseTracker {
    public static void trackUseCases(List<Integer> caseIds, Class<?> clazz){
        //利用反射机制，先找到目标类所有的方法，然后依次分析有没有待处理的注解，有的话就把注解类取出，进行处理
        for(Method m: clazz.getDeclaredMethods()){  //利用反射获取目标类里的方法
            //利用反射，获取方法m里的UseClass注解。
            //如果方法m没有加UseCase的注解，这里uc会是null。
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null){
                System.out.println("Find use case, id: " + uc.id() + ", description: " + uc.description());
            }else {
                System.out.println("Method m: " + m + " has no UseCase annotation!");
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> cases = new ArrayList<>();
        Collections.addAll(cases, 1, 2,3);
        trackUseCases(cases, PasswordUtils.class);
    }
}
