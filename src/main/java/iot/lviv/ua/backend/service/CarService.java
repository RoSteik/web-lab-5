/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: backend
 * Package: iot.lviv.ua.backend.service
 * Class: CarService
 */

package iot.lviv.ua.backend.service;

import iot.lviv.ua.backend.entity.CarEntity;
import iot.lviv.ua.backend.exception.CarAlreadyExistException;
import iot.lviv.ua.backend.exception.CarNotFoundException;
import iot.lviv.ua.backend.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    public CarEntity registration(CarEntity car) throws CarAlreadyExistException {
        if(carRepo.findByName(car.getName()) != null) {
            throw  new CarAlreadyExistException("user with that name already exists");
        }
        return carRepo.save(car);
    }

    public CarEntity getOne(Integer id) throws CarNotFoundException {
        CarEntity car = carRepo.findById(id).get();
        return car;
    }

    public List<CarEntity> getAll() {
        return (List<CarEntity>) carRepo.findAll();
    }

    public Integer delete(Integer id) {
        carRepo.deleteById(id);
        return id;
    }

    @Transactional
    public void update(Integer id, CarEntity car) {
        CarEntity newCar = carRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        newCar.setName(car.getName());
        newCar.setPrice(car.getPrice());
        newCar.setImage(car.getImage());
    }
}