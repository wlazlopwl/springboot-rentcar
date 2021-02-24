package com.appdevpwl.rentCar.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String imgurl;
    @Column(name = "production_year")
    private String productionYear;
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @Column(name = "rent_price")
    private Integer rentPrice;
    @Column(name = "doors_number")
    private Integer doorsNumber;
    @Column(name = "burning_fuel")
    private Double burningFuel;
    @ManyToMany
    @JoinTable(name = "car_extras",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "extras_id"))
    private Set<Extras> car_extras;

    public Car() {
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Extras> getCar_extras() {
        return car_extras;
    }

    public void setCar_extras(Set<Extras> carExtras) {
        this.car_extras = carExtras;
    }

    public void addExtras(Extras extras) {
        this.car_extras.add(extras);
        extras.getCars().add(this);
    }

    public Double getBurningFuel() {
        return burningFuel;
    }

    public void setBurningFuel(Double burningFuel) {
        this.burningFuel = burningFuel;
    }


    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(Integer doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
