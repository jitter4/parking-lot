package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.constants.enums.ParkingStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_tickets")
public class ParkingTicket extends BaseAbstractEntity {

    @Column(unique = true, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name= "in_time", nullable = false)
    private Date inTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name= "out_time")
    private Date outTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "parking_tickets__parking_spots__mapping",
            joinColumns = @JoinColumn(name = "parking_ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_spot_id"))
    private Collection<ParkingSpot> parkingSpots;

    @ManyToOne
    @JoinColumn(name = "parking_status_id", nullable = false)
    private ParkingTicketStatus parkingTicketStatus;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;

}
