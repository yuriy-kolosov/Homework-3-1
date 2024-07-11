package ru.hogwarts.school.homework_3.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.homework_3.service.InfoService;

import java.util.stream.Stream;

@Service
public class InfoServiceImpl implements InfoService {

    public InfoServiceImpl() {
    }

    @Override
    public int getValue1() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public long getTimeValue1() {
        long time1 = System.currentTimeMillis();
        Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }

    @Override
    public int getValue2() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
    }

    @Override
    public long getTimeValue2() {
        long time1 = System.currentTimeMillis();
        Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }

}
