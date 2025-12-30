package me.shurikennen.shopboardapi.controllers;


import lombok.RequiredArgsConstructor;
import me.shurikennen.shopboardapi.services.ServerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/servers")
@CrossOrigin
public class ServerController {

    private final ServerService serverService;

    @GetMapping("/dynmap/{server}")
    public ResponseEntity<String> getDynMapFormat(@PathVariable String server) {
        return ResponseEntity.ok(serverService.getDynMapFormat(server));
    }


}
