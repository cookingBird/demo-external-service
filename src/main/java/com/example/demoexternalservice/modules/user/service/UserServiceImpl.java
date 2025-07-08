package com.example.demoexternalservice.modules.user.service;

import com.example.demoexternalservice.commons.error.BizException;
import com.example.demoexternalservice.commons.error.CommonErrorType;
import com.example.demoexternalservice.modules.config.model.Configure;
import com.example.demoexternalservice.modules.config.repository.ConfigRepository;
import com.example.demoexternalservice.modules.external.service.ExternalService;
import com.example.demoexternalservice.modules.user.model.User;
import com.example.demoexternalservice.modules.user.repository.UserRepository;
import com.example.demoexternalservice.modules.user.web.response.UserResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<Long, User> {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ConfigRepository configRepository;

    @Resource
    ExternalService externalService;


    @Override
    public User save(User payload) {
        return userRepository.save(payload);
    }

    public Long save(User payload, Map<String, String> ext) {
        User saved = userRepository.save(payload);
        externalService.saveExternal("user", saved.getId().toString(), ext);
        return saved.getId();
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserResponse findAllById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())  throw new BizException(CommonErrorType.PARAM_INVALID);
        UserResponse userResponse = new UserResponse();
        userResponse.setRest(externalService.getExternal("user", id.toString()));
        user.ifPresent(s->{
            userResponse.setId(user.get().getId());
            userResponse.setName(user.get().getName());
            userResponse.setEmail(user.get().getEmail());
        });
        return userResponse;
    }
}
