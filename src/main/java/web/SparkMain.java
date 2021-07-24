package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdbc.Customer;
import jdbc.DataSourceFactory;
import jdbc.Order;
import jdbc.Person;
import org.json.simple.JSONArray;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static spark.Spark.*;

public class SparkMain {
    public static void main(String[] args) {
        port(4567);
        get("/hello/", (req, res) -> {
            String param=req.queryParams("name");
            res.status(201);
            return "Hello World "+param;});
        get("/hello/:name/word",(req,res)->{
            String param=req.params(":name");
            ObjectMapper objMap = new ObjectMapper();

            Response response = new Response("De la mine!"+param,null,1);
            res.status(201);
            String toJson = objMap.writeValueAsString(response);
            return toJson;
        });
        get("/person/:id",(req,res)->{
            String param=req.params(":id");
            int id = Integer.parseInt(param);
            ObjectMapper objMap = new ObjectMapper();
            DataSourceFactory dataSourceFactory = new DataSourceFactory();
            Connection connection = dataSourceFactory.getConnection();
            Customer customer1 = new Customer();
            customer1.getById(id);
            Person person1 = new Person();
            customer1.getById(3);
            res.status(201);
            String toJson = objMap.writeValueAsString(customer1);
            return toJson;
        });
        get("/insert/",(req,res)->{
            ObjectMapper objMap = new ObjectMapper();
            DataSourceFactory dataSourceFactory = new DataSourceFactory();
            Connection connection = dataSourceFactory.getConnection();
            Person person1 = new Person();
            person1.setId(9);
            person1.setFirstname("Ovidiu");
            person1.setLastname("Lipianu");
            person1.setAge(21);
            person1.insert();
            res.status(201);
            String toJson = objMap.writeValueAsString(person1);
            return toJson;
        });

        get("/delete/:id",(req,res)->{
            String param=req.params(":id");
            int id = Integer.parseInt(param);
            ObjectMapper objMap = new ObjectMapper();
            DataSourceFactory dataSourceFactory = new DataSourceFactory();
            Connection connection = dataSourceFactory.getConnection();
            Order order1 = new Order();
            order1.deleteById(id);
            res.status(201);
            String toJson = objMap.writeValueAsString(order1);
            return toJson;
        });

        get("/put/",(req,res)->{
            ObjectMapper objMap = new ObjectMapper();
            DataSourceFactory dataSourceFactory = new DataSourceFactory();
            Connection connection = dataSourceFactory.getConnection();
//            Customer customer1 = new Customer();
//            customer1.setId(2);
//            customer1.setAddress("Stada Bicaz");
//            customer1.setCity("Buzau");
//            customer1.setFisrtname("Bogdan");
//            customer1.setLastname("Dascalu");
//            customer1.setPhone("0773495959");
//            customer1.setPostalcode("03040");
//            customer1.setCountry("Romania");
//            customer1.setUsername("zBocdan");
            //customer1.put();
            Order order1 = new Order();
            order1.setId(4);
            order1.setOrder_date(java.sql.Date.valueOf("2015-06-07"));
            order1.setShipped_date(java.sql.Date.valueOf("2015-06-07"));
            order1.setStatus("shipped");
            order1.setComments("dfafsdsdf");
            order1.setCustomer_id(2);
            order1.put();
            res.status(201);
            String toJson = objMap.writeValueAsString(order1);
            return toJson;
        });

        get("/AllOrders/",(req,res)->{
            ObjectMapper objMap = new ObjectMapper();
            DataSourceFactory dataSourceFactory = new DataSourceFactory();
            Connection connection = dataSourceFactory.getConnection();
            Order person1 = new Order();
            List<Order> rez = person1.allOrders();
            res.status(201);
            String toJson = JSONArray.toJSONString(rez);
            return toJson;
        });

        get("/ordersOfClient/:id",(req,res)->{
            String param=req.params(":id");
            int id = Integer.parseInt(param);
            ObjectMapper objMap = new ObjectMapper();
            DataSourceFactory dataSourceFactory = new DataSourceFactory();
            Connection connection = dataSourceFactory.getConnection();
            Order order1 = new Order();
            List<Order> rez = order1.ordersOfCustumer(id);
            res.status(201);
            String toJson = JSONArray.toJSONString(rez);
            return toJson;
        });
    }
}

