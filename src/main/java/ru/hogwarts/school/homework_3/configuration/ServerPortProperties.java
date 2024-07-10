package ru.hogwarts.school.homework_3.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "server")
public class ServerPortProperties {

    private int port;

    public ServerPortProperties() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServerPortProperties{" +
                "port=" + port +
                '}';
    }

}
