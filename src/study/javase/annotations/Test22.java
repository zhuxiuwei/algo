package study.javase.annotations;

import java.util.ArrayList;
import java.util.List;

class AppleSmall extends Apple{}
class Apple extends Fruit{}
class Fruit{}

public class Test22 {

    static <T> void add(List<T> l, T item){
        l.add(item);
        System.out.println(item);
    }

    static void add2(List<? extends Fruit> f){
//        f.add(new Apple());
        f.add(null);
    }

    static void add3(List<? super Fruit> f){
        f.add(new Apple());

    }

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<Fruit>();
        Apple a = new Apple();
        fruits.add(a);
        Fruit f = fruits.get(0);
        System.out.println(f);


        List<? super Fruit> f23 = new ArrayList<Fruit>();
        f23.add(new Apple());
        System.out.println(f23.get(0));

//        List<? super Apple> f33 =  new ArrayList<AppleSmall>();   //本行编译错误

    }
}
