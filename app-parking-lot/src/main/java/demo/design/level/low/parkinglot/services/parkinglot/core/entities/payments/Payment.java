package demo.design.level.low.parkinglot.services.parkinglot.core.entities.payments;

import demo.design.level.low.parkinglot.services.parkinglot.core.constants.enums.TransactionStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment extends BaseAbstractEntity {

    private String transactionId;

    private Double amount;

    @Enumerated()
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "parking_status_id", nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "parking_mode_id")
    private PaymentMode paymentMode;

    private Double payedAmount;
    private Date payedAt;

}
