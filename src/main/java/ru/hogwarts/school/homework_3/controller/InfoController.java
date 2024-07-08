package ru.hogwarts.school.homework_3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.homework_3.service.ServerService;

@RestController
@RequestMapping("/port")
public class InfoController {

    public final ServerService serverService;

    public InfoController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping()
    public ResponseEntity<Integer> getServerPort() {
        Integer serverPort = serverService.serverPort();
        return ResponseEntity.ok(serverPort);
    }

}
