package me.shurikennen.shopboardapi.repositories;

import me.shurikennen.shopboardapi.entities.MinecraftServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<MinecraftServer, Long> {

    MinecraftServer getMinecraftServerByServerHash(String serverHash);

}
