package jdbc;

import java.sql.SQLException;

public class MainReflection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Person person1=new Person();
    person1.setId(9);
    person1.setFirstname("Ovidiu");
    person1.setLastname("Lipianu");
    person1.setAge(21);
    person1.insert();
    }

}
