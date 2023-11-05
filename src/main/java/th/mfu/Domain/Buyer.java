package th.mfu.Domain;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;
    private long Id;

    @OneToMany(cascade = CascadeType.ALL)
    private Order order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    

}
