package study.javase.annotations;

public class Testable {
    public void execute() {
        System.out.println("Executing...");
    }

    //使用注解
    @Test void testExecute(){
        execute();
    }
}
