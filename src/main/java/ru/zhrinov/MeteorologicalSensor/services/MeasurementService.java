package ru.zhrinov.MeteorologicalSensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhrinov.MeteorologicalSensor.models.Measurement;
import ru.zhrinov.MeteorologicalSensor.models.Sensor;
import ru.zhrinov.MeteorologicalSensor.repositories.MeasurementRepository;
import ru.zhrinov.MeteorologicalSensor.util.excetions.ObjectNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Transactional
    public void saveMeasurement(Measurement measurement) {
        Sensor sensorByName = sensorService.getSensorByName(measurement.getSensor().getName())
                .orElseThrow(ObjectNotFoundException::new);
        measurement.setLocalDateTimeOfMeasurement(LocalDateTime.now());
        measurement.setSensor(sensorByName);
        sensorByName.getMeasurements().add(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> getAllMeasurement() {
        return measurementRepository.findAll();
    }

    public int getRainyDays() {
        return measurementRepository.countMeasurementByRainingTrue();
    }
}
