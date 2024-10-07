package ru.zhrinov.MeteorologicalSensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zhrinov.MeteorologicalSensor.dto.MeasurementDTO;
import ru.zhrinov.MeteorologicalSensor.models.Measurement;
import ru.zhrinov.MeteorologicalSensor.services.MeasurementService;
import ru.zhrinov.MeteorologicalSensor.util.ModelMapperUtil;
import ru.zhrinov.MeteorologicalSensor.util.excetions.ErrorObjectResponse;
import ru.zhrinov.MeteorologicalSensor.util.excetions.ObjectNotFoundException;

import java.util.List;

import static ru.zhrinov.MeteorologicalSensor.util.excetions.ErrorObjectResponse.showExceptionCreateObject;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapperUtil modelMapperUtil;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasurement(
            @RequestBody @Valid MeasurementDTO measurementDTO, BindingResult measurementBindingResult) {

        showExceptionCreateObject(measurementBindingResult);
        Measurement measurement = modelMapperUtil.convertToEntity(measurementDTO, Measurement.class);

        measurementService.saveMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurement() {
        return measurementService.getAllMeasurement().stream()
                .map(m -> modelMapperUtil.convertToEntity(m, MeasurementDTO.class)).toList();
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<String> rainyDaysCount() {
        int countDays = measurementService.getRainyDays();
        return ResponseEntity.ok("Rain days: " + countDays);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorObjectResponse> handlerException(ObjectNotFoundException e) {
        ErrorObjectResponse response = new ErrorObjectResponse("Sensor = " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
