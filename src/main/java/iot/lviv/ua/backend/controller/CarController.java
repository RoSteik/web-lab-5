/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: backend
 * Package: iot.lviv.ua.backend.controller
 * Class: CarController
 */

package iot.lviv.ua.backend.controller;

import iot.lviv.ua.backend.entity.CarEntity;
import iot.lviv.ua.backend.exception.CarAlreadyExistException;
import iot.lviv.ua.backend.exception.CarNotFoundException;
import iot.lviv.ua.backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity registration(@RequestBody CarEntity car) {
        try {
            carService.registration(car);
            return ResponseEntity.ok("user saved successfully");
        } catch (CarAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }

    @CrossOrigin
    @GetMapping
    private List<CarEntity> getAll() {
        return carService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity getOneCar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(carService.getOne(id));
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(carService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    private void update(@PathVariable("id") Integer id, @RequestBody CarEntity car) {
        carService.update(id, car);
    }
}