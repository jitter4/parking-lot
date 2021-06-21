package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_display_boards")
public class ParkingDisplayBoard extends BaseAbstractEntity {

    @Column(unique = true)
    private int number;

    private int handicappedFreeSpots;
    private int compactFreeSpots;
    private int largeFreeSpots;
    private int motorbikeFreeSpots;
    private int electricFreeSpots;


    @OneToMany(mappedBy = "parkingDisplayBoard", fetch = FetchType.LAZY)
    private Set<EntrancePanel> entrancePanel;

    @OneToOne
    private ParkingLot parkingLot;

}
