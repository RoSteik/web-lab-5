/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: backend
 * Package: iot.lviv.ua.backend.repository
 * Class: CarRepo
 */

package iot.lviv.ua.backend.repository;

import iot.lviv.ua.backend.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo  extends CrudRepository<CarEntity, Integer> {
    CarEntity findByName(String name);
}
