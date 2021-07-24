package jdbc;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@ActiveRecordEntity(tablename="orders",keyColumnName="id")
public class Order extends ActiveRecord{
    public int id;
    public java.sql.Date order_date;
    public java.sql.Date shipped_date;
    public String stat;
    public String comments;
    public int customer_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder_date(java.sql.Date order_date) {
        this.order_date = order_date;
    }

    public void setShipped_date(java.sql.Date shipped_date) {
        this.shipped_date = shipped_date;
    }

    public void setStatus(String status) {
        this.stat = status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_date=" + order_date +
                ", shipped_date=" + shipped_date +
                ", status='" + stat + '\'' +
                ", comments='" + comments + '\'' +
                ", customer_id=" + customer_id +
                '}';
    }
}