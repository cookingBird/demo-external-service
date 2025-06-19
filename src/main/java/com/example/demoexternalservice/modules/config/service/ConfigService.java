package com.example.demoexternalservice.modules.config.service;

import com.example.demoexternalservice.modules.config.model.Configure;
import com.example.demoexternalservice.modules.config.repository.ConfigRepository;
import com.example.demoexternalservice.utils.EntityUtils;
import io.netty.util.internal.StringUtil;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public Configure saveConfigure(Configure configure) {
        return configRepository.save(configure);
    }

    public Configure updateConfigure(Long id, Configure configure) {
        Optional<Configure> exist = configRepository.findById(id);
        if(!exist.isPresent()){
            throw new RuntimeException("not found");
        }
        return configRepository.save(configure);
    }
    public Configure patchConfigure(Long id, Configure configure) {
        Optional<Configure> exist = configRepository.findById(id);
        if (exist.isPresent()) {
            var pre = exist.get();
            EntityUtils.getMethodsForEach(configure, (forEachPayload) -> {
                if (forEachPayload.getFieldValue().isPresent() &&  StringUtil.isNullOrEmpty(forEachPayload.getFieldValue().get().toString())) {
                    try {
                        EntityUtils.setFieldValue(pre, forEachPayload.getFiledName(), forEachPayload.getFieldValue().get());
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return configRepository.save(pre);
        }
        return null;
    }

    public Configure getConfigureById(Long id) {
        return configRepository.findById(id).orElse(null);
    }

    public List<Configure> getTableConfig(String tableName) {
        return configRepository.findByTableName(tableName);
    }

    ;

    public Page<Configure> getAllConfig(int page, int size) {
        return configRepository.findAll(PageRequest.of(page, size));
    }
}
