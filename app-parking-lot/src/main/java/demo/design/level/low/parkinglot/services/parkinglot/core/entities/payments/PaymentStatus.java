package demo.design.level.low.parkinglot.services.parkinglot.core.entities.payments;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment_status")
public class PaymentStatus extends BaseAbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
