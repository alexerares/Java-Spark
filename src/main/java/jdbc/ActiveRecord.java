package jdbc;

// create , update , read , delete
// insert into , values

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ActiveRecord {

    // INSERT INTO persons(id, firstname,lastname,age) VALUES (?,  ? , ? , ?);
    public boolean insert() throws SQLException, ClassNotFoundException, IllegalAccessException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        Field[] fields = this.getClass().getFields();
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO " + arAnnotation.tablename() + " (");
        byte fieldsCount = 0;
        for (Field field : fields) {
            fieldsCount++;
            query.append(field.getName() + ",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(") VALUES (");
        for (int i = 0; i < fieldsCount; i++) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(");");
        DataSourceFactory factory=new DataSourceFactory();
        Connection connection = factory.getConnection();
        PreparedStatement ptstmt = connection.prepareStatement(query.toString());
        byte count = 1;
        for (Field field : fields) {
            if(field.getType() == int.class){
                ptstmt.setInt(count++, field.getInt(this));
            } else if(field.getType() == String.class) {
                ptstmt.setString(count++, (String) field.get(this));
            }
        }
        ptstmt.execute();
        connection.commit();
        return true;
    }

    public boolean getById(int id) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        DataSourceFactory factory = new DataSourceFactory();
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement st = conn.prepareStatement("select * from " + arAnnotation.tablename() + " where id = ?");
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        if(!res.isBeforeFirst()){
            return false;
        } else {
            res.next();
            ResultSetMetaData metadata = res.getMetaData();
            int colCount = metadata.getColumnCount();
            for(int i=1;i<colCount+1;i++){
                Field f = c.getField(metadata.getColumnName(i).toLowerCase());
                if(f.getType()==int.class){
                    f.setInt(this, res.getInt(i));
                } else
                if(f.getType()==byte.class){
                    f.setByte(this, res.getByte(i));
                } else
                if(f.getType()==String.class){
                    f.set(this, res.getString(i));
                }  else
                if(f.getType()==Date.class){
                    f.set(this, res.getDate(i));
                }
            }
        }
        return true;
    }

    public boolean deleteById(int id) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        DataSourceFactory factory = new DataSourceFactory();
        Connection conn = DataSourceFactory.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("delete from " + arAnnotation.tablename() + " where id = ?;");
        PreparedStatement ptstmt = conn.prepareStatement(query.toString());
        ptstmt.setInt(1,id);
        ptstmt.executeUpdate();
        conn.commit();
        return true;

    }

    public boolean put() throws SQLException, ClassNotFoundException, IllegalAccessException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        Field[] fields = this.getClass().getFields();
        StringBuilder query = new StringBuilder();
        //INSERT INTO  `db-school`.persons(id, firstname, lastname, age) VALUES(3, "Alex", "Maria",22) ON DUPLICATE KEY UPDATE
        //firstname="Alex", lastname="Maria",age=19;
        query.append("INSERT INTO " + arAnnotation.tablename() + " (");
        byte fieldsCount = 0;
        for (Field field : fields) {
            fieldsCount++;
            query.append(field.getName() + ",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(") VALUES (");
        for (int i = 0; i < fieldsCount; i++) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(") ON DUPLICATE KEY UPDATE ");
        boolean ver = true;
        for (Field field : fields) {
            if(ver) {
                ver = false;
                continue;
            }
            query.append(field.getName() + "=?,");
        }
        query.deleteCharAt(query.length() - 1);
        System.out.println(";");
        DataSourceFactory factory=new DataSourceFactory();
        Connection connection = factory.getConnection();
        PreparedStatement ptstmt = connection.prepareStatement(query.toString());
        byte count = 1;
        for (Field field : fields) {
            if(field.getType() == int.class){
                System.out.println(field.getName());
                ptstmt.setInt(count++, field.getInt(this));
            } else if(field.getType() == String.class) {
                ptstmt.setString(count++, (String) field.get(this));
            }else
            if(field.getType()==Date.class){
                ptstmt.setDate(count++, (java.sql.Date)field.get(this));
            }
        }
        ver = true;
        for (Field field : fields) {
            if(ver) {
                ver = false;
                continue;
            }
            if(field.getType() == int.class){
                ptstmt.setInt(count++, field.getInt(this));
            } else if(field.getType() == String.class) {
                ptstmt.setString(count++, (String) field.get(this));
            }
            else
            if(field.getType()==Date.class){
                ptstmt.setDate(count++,(java.sql.Date) field.get(this));
            }
        }
        System.out.println(ptstmt);
        ptstmt.execute();
        connection.commit();
        return true;
    }

    public List<Customer> allCustomers() throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        //update `db-school`.persons set firstname="Rares", lastname = "Alexe" where id=2;
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        Connection connection = dataSourceFactory.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("select * from " + arAnnotation.tablename() + ";");
        ResultSet res = pstmt.executeQuery();
        List<Customer> list = new ArrayList<>();
        if(!res.isBeforeFirst()){
            return null;
        } else {
            while (res.next()){
                ResultSetMetaData metaData = res.getMetaData();
                int colCount = metaData.getColumnCount();
                for(int i=1;i<colCount+1;i++){
                    Field f = c.getField(metaData.getColumnName(i).toLowerCase());
                    if (f.getType() == int.class) {
                        f.setInt(this, res.getInt(i));
                    } else if (f.getType() == String.class) {
                        f.set(this, res.getString(i));
                    }else if (f.getType() == double.class) {
                        f.setDouble(this, res.getDouble(i));
                    }

                }
                list.add((Customer) this);
            }
        }
        return list;
    }

    public List<Order> allOrders() throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        //update `db-school`.persons set firstname="Rares", lastname = "Alexe" where id=2;
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        Connection connection = dataSourceFactory.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("select * from " + arAnnotation.tablename() + ";");
        ResultSet res = pstmt.executeQuery();
        List<Order> list = new ArrayList<>();
        if(!res.isBeforeFirst()){
            return null;
        } else {
            while (res.next()){
                ResultSetMetaData metaData = res.getMetaData();
                int colCount = metaData.getColumnCount();
                for(int i=1;i<colCount+1;i++){
                    Field f = c.getField(metaData.getColumnName(i).toLowerCase());
                    if (f.getType() == int.class) {
                        f.setInt(this, res.getInt(i));
                    } else if (f.getType() == String.class) {
                        f.set(this, res.getString(i));
                    }else if (f.getType() == double.class) {
                        f.setDouble(this, res.getDouble(i));
                    }

                }
                list.add((Order) this);
            }
        }
        return list;
    }

    public List<Order> ordersOfCustumer(int id) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<ActiveRecord> c = (Class<ActiveRecord>) this.getClass();
        ActiveRecordEntity arAnnotation = (ActiveRecordEntity) c.getAnnotation(ActiveRecordEntity.class);
        //update `db-school`.persons set firstname="Rares", lastname = "Alexe" where id=2;
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        Connection connection = dataSourceFactory.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("select * from " + arAnnotation.tablename() + " where customer_id = " + id + ";");
        ResultSet res = pstmt.executeQuery();
        List<Order> list = new ArrayList<>();
        if(!res.isBeforeFirst()){
            return null;
        } else {
            while (res.next()){
                ResultSetMetaData metaData = res.getMetaData();
                int colCount = metaData.getColumnCount();
                for(int i=1;i<colCount+1;i++){
                    Field f = c.getField(metaData.getColumnName(i).toLowerCase());
                    if (f.getType() == int.class) {
                        f.setInt(this, res.getInt(i));
                    } else if (f.getType() == String.class) {
                        f.set(this, res.getString(i));
                    }else if (f.getType() == double.class) {
                        f.setDouble(this, res.getDouble(i));
                    }

                }
                list.add((Order) this);
            }
        }
        return list;
    }
}
