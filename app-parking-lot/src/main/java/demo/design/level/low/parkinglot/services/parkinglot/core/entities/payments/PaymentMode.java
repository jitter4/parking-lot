package demo.design.level.low.parkinglot.services.parkinglot.core.entities.payments;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.payments.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_modes")
public class PaymentMode extends BaseAbstractEntity {

    private String name;

    @OneToMany(mappedBy = "paymentMode", fetch = FetchType.LAZY)
    private Set<Payment> payments;

}
