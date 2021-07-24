package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAdministrator;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAttendent;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ExitPanel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_lots")
public class ParkingLot extends BaseAbstractEntity {

    @Column(unique = true, nullable = false)
    private String code;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingFloor> parkingFloors;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingSpot> parkingSpots;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingRate> parkingRates;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingSpotCount> parkingSpotCounts;

    @OneToOne
    @JoinColumn(name = "parking_display_board_id", referencedColumnName = "id")
    private ParkingDisplayBoard parkingDisplayBoard;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<EntrancePanel> entrancePanels;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ExitPanel> exitPanels;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingTicket> parkingTickets;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingLotAttendent> parkingLotAttendents;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingLotAttendentPortal> parkingLotAttendentPortals;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<VehicleTypeParkingSpotCount> vehicleTypeParkingSpotCounts;


    @ManyToOne
    @JoinColumn(name = "parking_lot_status_id", nullable = false)
    private ParkingLotStatus parkingLotStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "parking_lot_admins",
            joinColumns = @JoinColumn(name = "parking_lot_id"),
            inverseJoinColumns = @JoinColumn(name = "administrator_id"))
    private Collection<ParkingLotAdministrator> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingLot)) return false;
        if (!super.equals(o)) return false;
        ParkingLot that = (ParkingLot) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
