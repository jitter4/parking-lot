package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTicketStatusRepository extends JpaRepository<ParkingTicketStatus, Long> {

    ParkingTicketStatus findByName(String name);

}
