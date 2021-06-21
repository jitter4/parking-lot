package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.api.Card;
import demo.design.level.low.parkinglot.services.parkinglot.core.api.Cash;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ElectricPanel;

public interface ElectricPanelService {

    ElectricPanel payForMinutes(ElectricPanel electricPanel, int minutes, Cash cash);
    ElectricPanel payForMinutes(ElectricPanel electricPanel, int minutes, Card card);
    ElectricPanel cancelCharging(ElectricPanel electricPanel);

}
