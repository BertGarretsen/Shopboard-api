package me.shurikennen.shopboardapi.services;

import lombok.RequiredArgsConstructor;
import me.shurikennen.shopboardapi.repositories.ServerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;


    public String getDynMapFormat(String serverHash) {
        return serverRepository.getMinecraftServerByServerHash(serverHash).getDynMapFormat();
    }


}
