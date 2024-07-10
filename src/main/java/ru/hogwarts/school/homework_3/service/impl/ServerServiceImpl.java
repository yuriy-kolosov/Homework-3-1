package ru.hogwarts.school.homework_3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.configuration.ServerPortProperties;
import ru.hogwarts.school.homework_3.service.ServerService;

@Service
public class ServerServiceImpl implements ServerService {

    private final ServerPortProperties serverPortProperties;

    public ServerServiceImpl(ServerPortProperties serverPortProperties) {
        this.serverPortProperties = serverPortProperties;
    }

    Logger logger = LoggerFactory.getLogger(ServerServiceImpl.class);

    @Override
    public int serverPort() {
        logger.info("\"Get server port\" method was invoke...");
        return serverPortProperties.getPort();
    }

}
