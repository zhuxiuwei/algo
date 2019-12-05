package study.javase.annotations;

/**
 * 注解使用测试
 */
public class PasswordUtils {
    
    @UseCase(id = 1, description = "check password correct")
    public boolean validatePassword(String pwd){
        return pwd.equals("123456");
    }

    @UseCase(id = 2)
    public int hashPassword(String pwd){
        return pwd.hashCode();
    }

    //没有使用注解的类
    public void foo(){
        System.out.println("do something...");
    }
}
