package com.example.demoexternalservice.modules.external.service;

import com.example.demoexternalservice.modules.config.model.Configure;
import com.example.demoexternalservice.modules.config.repository.ConfigRepository;
import com.example.demoexternalservice.modules.external.model.KeyValuePair;
import com.example.demoexternalservice.modules.external.repository.KeyValueRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExternalService {

    @Resource
    private EntityManager entityManager;

    @Resource
    private BatchService batchService;

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
    private final KeyValueRepository keyValueRepository;

    private final ConfigRepository configRepository;

    public ExternalService(KeyValueRepository keyValueRepository, ConfigRepository configRepository) {
        this.keyValueRepository = keyValueRepository;
        this.configRepository = configRepository;
    }

    public void saveExternal(String tableName, String rowId, Map<String, String> payload) {
        List<Configure> externalConfig = configRepository.findByTableName(tableName);
        List<KeyValuePair> collect = payload.entrySet()
                .stream()
                .filter(entry -> externalConfig.stream().anyMatch(c -> c.getFieldName().equals(entry.getKey())))
                .map(entry -> {
                    return KeyValuePair.builder()
                            .entityKey(rowId)
                            .modelKey(tableName)
                            .field(entry.getKey())
                            .value(entry.getValue())
                            .build();
                }).collect(Collectors.toList());
        batchService.batchInsert(collect);

    }

    public Map<String, String> getExternal(String tableName, String rowId) {
        List<Configure> userModelConfig = configRepository.findByTableName(tableName);
        List<KeyValuePair> externals = keyValueRepository.findByRowIdAndTableName(rowId, tableName);
        return externals.stream()
                .filter(kp -> userModelConfig.stream().anyMatch(c -> c.getFieldName().equals(kp.getField())))
                .collect(Collectors.toMap(KeyValuePair::getField, KeyValuePair::getValue));
    }
}
