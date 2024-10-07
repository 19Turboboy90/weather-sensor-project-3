package ru.zhrinov.MeteorologicalSensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zhrinov.MeteorologicalSensor.dto.SensorDTO;
import ru.zhrinov.MeteorologicalSensor.models.Sensor;
import ru.zhrinov.MeteorologicalSensor.services.SensorService;
import ru.zhrinov.MeteorologicalSensor.util.ModelMapperUtil;
import ru.zhrinov.MeteorologicalSensor.util.SensorValidator;
import ru.zhrinov.MeteorologicalSensor.util.excetions.ErrorObjectResponse;
import ru.zhrinov.MeteorologicalSensor.util.excetions.ObjectNotCreatedException;

import static ru.zhrinov.MeteorologicalSensor.util.excetions.ErrorObjectResponse.showExceptionCreateObject;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapperUtil modelMapperUtil;
    private final SensorValidator sensorValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody() @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensor = modelMapperUtil.convertToEntity(sensorDTO, Sensor.class);
        sensorValidator.validate(sensor, bindingResult);
        showExceptionCreateObject(bindingResult);

        sensorService.saveSensor(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorObjectResponse> handlerException(ObjectNotCreatedException e) {
        ErrorObjectResponse response = new ErrorObjectResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
