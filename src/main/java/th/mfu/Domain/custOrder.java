package th.mfu.Domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class custOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    
    @OneToOne(cascade = CascadeType.ALL)
    private Rider rider;

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;

    public Long getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
        
}