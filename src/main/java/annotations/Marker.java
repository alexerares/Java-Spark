package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker{}

public class Marker {

    public int id;
    public String firstName;
    public String lastName;

    @MyMarker
    public static void myMethod(){
        Marker markerObj = new Marker();
        try{
            Method m = markerObj.getClass().getMethod("myMethod");
            Field[] fields=markerObj.getClass().getFields();
            System.out.println(fields.length);
            for(Field field:fields)
                System.out.println(field.getName());
//            System.out.println(m.getName() +" "+ m.getAnnotatedReturnType()+" "+m.getDeclaringClass());
            if(m.isAnnotationPresent(MyMarker.class)){
                System.out.println("@MyMarker is present");
            }
            else{
                System.out.println("@MyMarker is not present");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        myMethod();
    }
}
