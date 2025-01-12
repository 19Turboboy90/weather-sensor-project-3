package ru.zhrinov.MeteorologicalSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhrinov.MeteorologicalSensor.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Integer countMeasurementByRainingTrue();
}
