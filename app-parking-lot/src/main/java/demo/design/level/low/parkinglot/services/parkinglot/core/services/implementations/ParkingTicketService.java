package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingTicketRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingTicketService implements IParkingTicketService {

    private final ParkingTicketRepository parkingTicketRepository;

    @Autowired
    public ParkingTicketService(final ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
    }

    @Override
    public ParkingTicket save(final ParkingTicket ticket) {
        return this.parkingTicketRepository.save(ticket);
    }

    @Override
    public ParkingTicket findByNumber(final String number) {
        return this.parkingTicketRepository.findByNumber(number);
    }
}
