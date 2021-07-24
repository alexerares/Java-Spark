package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MySingle{
    int value();
    String name();
}

public class Single {
    @MySingle(value = 10,name = "Ovidiu")
    public static void myMethod(){
        Single object=new Single();
        try{
            Method m=object.getClass().getMethod("myMethod");
            MySingle anno=m.getAnnotation(MySingle.class);
            System.out.println(anno.value());
            System.out.println(anno.name());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        myMethod();
    }

}
