package ru.hogwarts.school.homework_3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.homework_3.service.InfoService;
import ru.hogwarts.school.homework_3.service.ServerService;

@RestController
@RequestMapping("/port")
public class InfoController {

    public final InfoService infoService;
    public final ServerService serverService;

    public InfoController(InfoService infoService, ServerService serverService) {
        this.infoService = infoService;
        this.serverService = serverService;
    }

    @GetMapping()
    public ResponseEntity<Integer> getServerPort() {
        Integer serverPort = serverService.serverPort();
        return ResponseEntity.ok(serverPort);
    }

    @GetMapping("/value1")
    public ResponseEntity<Integer> getValueBySum1() {
        Integer value = infoService.getValue1();
        return ResponseEntity.ok(value);
    }

    @GetMapping("/value1/time")
    public ResponseEntity<Long> getTimeValueBySum1() {
        Long value = infoService.getTimeValue1();
        return ResponseEntity.ok(value);
    }

    @GetMapping("/value2")
    public ResponseEntity<Integer> getValueBySum2() {
        Integer value = infoService.getValue2();
        return ResponseEntity.ok(value);
    }

    @GetMapping("/value2/time")
    public ResponseEntity<Long> getTimeValueBySum2() {
        Long value = infoService.getTimeValue2();
        return ResponseEntity.ok(value);
    }

}
