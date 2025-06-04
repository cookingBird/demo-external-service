package com.example.demoexternalservice.modules.external.service;

import com.example.demoexternalservice.modules.external.model.KeyValuePair;
import com.example.demoexternalservice.modules.external.repository.KeyValueRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExternalService {
    private final KeyValueRepository keyValueRepository;

    public ExternalService(KeyValueRepository keyValueRepository) {
        this.keyValueRepository = keyValueRepository;
    }

    public void saveExternal(String tableName, String rowId, Map<String, String> payload) {
        for (Map.Entry<String, String> entry : payload.entrySet()) {
            KeyValuePair keyValuePair = KeyValuePair.builder()
                    .rowId(rowId)
                    .tableName(tableName)
                    .key(entry.getKey())
                    .value(entry.getValue())
                    .build();
            keyValueRepository.save(keyValuePair);
        }
    }

    public Map<String, String> getExternal(String tableName, String rowId) {
        return keyValueRepository.findByRowIdAndTableName(rowId, tableName).stream()
                .collect(Collectors.toMap(KeyValuePair::getKey, KeyValuePair::getValue));
    }
}
