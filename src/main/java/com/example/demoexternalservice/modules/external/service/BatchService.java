package com.example.demoexternalservice.modules.external.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BatchService {

    @Resource
    private EntityManager entityManager;

    @Transactional
    public <T> void batchInsert(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            entityManager.persist(list.get(i));

            // 每 50 条刷一次，防止内存溢出
            if (i % 50 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
