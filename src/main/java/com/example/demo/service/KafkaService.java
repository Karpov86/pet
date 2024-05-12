package com.example.demo.service;

import java.util.List;

public interface KafkaService<E> {

    void send(List<E> payments);

}
