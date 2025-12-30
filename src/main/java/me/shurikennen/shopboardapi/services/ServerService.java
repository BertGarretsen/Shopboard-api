package me.shurikennen.shopboardapi.services;

import lombok.RequiredArgsConstructor;
import me.shurikennen.shopboardapi.entities.MinecraftServer;
import me.shurikennen.shopboardapi.repositories.ServerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;


    public String getDynMapFormat(String serverHash) {
        MinecraftServer server = serverRepository.getMinecraftServerByServerHash(serverHash);
        if (server == null) return null;
        return server.getDynMapFormat();
    }


}
