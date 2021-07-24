package jdbc;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@ActiveRecordEntity(tablename="customers",keyColumnName="id")
public class Customer extends ActiveRecord{
    public int id;
    public String username;
    public String last_name;
    public String first_name;
    public String phone;
    public String address;
    public String city;
    public String postalcode;
    public String country;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLastname(String lastname) {
        this.last_name = lastname;
    }

    public void setFisrtname(String fisrtname) {
        this.first_name = fisrtname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastname='" + last_name + '\'' +
                ", fisrtname='" + first_name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}