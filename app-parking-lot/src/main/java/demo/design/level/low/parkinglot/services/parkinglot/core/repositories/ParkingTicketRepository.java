package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    ParkingTicket findByNumber(String number);

    @Query(value = "select * from parking_ticket where in_Time >= :inTime  and (out_time <= :outTime || out_time is NULL)", nativeQuery = true)
    Page<ParkingTicket> getByDuration(@Param("inTime") Date inTme, @Param("outTime") Date outTime, Pageable pageable);

}
