package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address extends BaseAbstractEntity {

    private String streetAddress;
    private String street;
    private String area;
    private String policeStation;
    private String city;
    private String district;
    private String state;
    private String country;

    @Column(length = 6)
    private String pinCode;

    @Column(length = 32)
    private String geoCode;

}
