package com.example.demoexternalservice.modules.config.service;

import com.example.demoexternalservice.commons.error.BizException;
import com.example.demoexternalservice.commons.error.CommonErrorType;
import com.example.demoexternalservice.modules.config.model.Configure;
import com.example.demoexternalservice.modules.config.repository.ConfigRepository;
import com.example.demoexternalservice.utils.EntityUtils;
import io.netty.util.internal.StringUtil;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        if (!exist.isPresent()) throw new BizException(CommonErrorType.PARAM_INVALID);
        return configRepository.save(configure);
    }

    public Configure patchConfigure(Long id, Configure configure) {
        Optional<Configure> exist = configRepository.findById(id);
        if (!exist.isPresent()) throw new BizException(CommonErrorType.PARAM_INVALID);
        var pre = exist.get();
        EntityUtils.getMethodsForEach(configure, (fieldName, filedValue) -> {

            if (Objects.nonNull(filedValue)
                    && !StringUtil.isNullOrEmpty(filedValue.toString().trim())) {

                EntityUtils.setFieldValue(pre, fieldName, filedValue);

            }
        });
        return configRepository.save(pre);
    }


    public Configure getConfigureById(Long id) {
        return configRepository.findById(id).orElse(null);
    }

    public List<Configure> getTableConfig(String tableName) {
        return configRepository.findByTableName(tableName);
    }

    public void deleteById(Long id) {
        configRepository.deleteById(id);
    }

    public Page<Configure> getAllConfig(int page, int size) {
        return configRepository.findAll(PageRequest.of(page, size));
    }
}
