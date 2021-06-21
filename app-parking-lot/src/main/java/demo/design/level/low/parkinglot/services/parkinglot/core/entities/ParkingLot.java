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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_lots")
public class ParkingLot extends BaseAbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private String displayName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingFloor> parkingFloors;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingSpot> parkingSpots;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingRate> parkingRates;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingSpotCount> parkingSpotCounts;

    @OneToOne
    @JoinColumn(name = "parking_display_board_id", referencedColumnName = "id")
    private ParkingDisplayBoard parkingDisplayBoard;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EntrancePanel> entrancePanels;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExitPanel> exitPanels;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingTicket> parkingTickets;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingLotAttendent> parkingLotAttendents;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingLotAttendentPortal> parkingLotAttendentPortals;

    @ManyToOne
    @JoinColumn(name = "parking_lot_status_id", nullable = false)
    private ParkingLotStatus parkingLotStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "parking_lot_admins",
            joinColumns = @JoinColumn(name = "parking_lot_id"),
            inverseJoinColumns = @JoinColumn(name = "administrator_id"))
    private Set<ParkingLotAdministrator> roles = new HashSet<>();

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
