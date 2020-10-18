package com.internalproject.api.services.impl;

import com.internalproject.api.domain.requests.UserCreateRequest;
import com.internalproject.api.email.EmailService;
import com.internalproject.api.email.factory.EmailFactory;
import com.internalproject.api.email.templates.EmailTemplate;
import com.internalproject.api.enums.Language;
import com.internalproject.api.enums.MessageType;
import com.internalproject.api.enums.UserStatus;
import com.internalproject.api.exceptions.models.ApiException;
import com.internalproject.api.model.entity.user.Role;
import com.internalproject.api.model.entity.user.RoleType;
import com.internalproject.api.model.entity.user.User;
import com.internalproject.api.repository.user.RoleRepository;
import com.internalproject.api.repository.user.UserRepository;
import com.internalproject.api.services.UserService;
import com.internalproject.api.utils.NotificationUtil;
import com.internalproject.api.verification.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.internalproject.api.exceptions.ExceptionContent.INVALID_VERIFICATION_KEY;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final EmailFactory templateFactory;
    private final VerificationService verificationService;
    private final NotificationUtil messageUtil;

    @Value("${link.url}")
    private String url;

    @Value("${link.account.verify}")
    private String verificationLinks;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, EmailService emailService, EmailFactory templateFactory, VerificationService verificationService, NotificationUtil messageUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.templateFactory = templateFactory;
        this.verificationService = verificationService;
        this.messageUtil = messageUtil;
    }

    @Transactional
    @Override
    public User create(UserCreateRequest request) {
        boolean existsUserByUserName = userRepository.existsUserByUserNameAndEmail(request.getUsername(), request.getEmail());
        if (existsUserByUserName) {
            throw new IllegalArgumentException("User with username " + request.getUsername() + "email "+ request.getEmail() + " already existed!");
        }

        User user = new User();
        String verificationKey = verificationService.generateKey(request.getEmail());
        user.setVerificationKey(verificationKey);
        user.setUserName(request.getUsername());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setStatus(UserStatus.WAITING_FOR_CONFIRMATION);
        user.setRoles(toUserRoles(Set.of(RoleType.PRE_ACTIVE.name())));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Language language = Language.RU;
        EmailTemplate template = templateFactory.getAccountVerifiedTemplate();
        template.setLanguage(language);
        template.setLink(url + String.format(verificationLinks, verificationKey, language));
        emailService.sendEmail(user.getEmail(), messageUtil.getByType(MessageType.VERIFY_EMAIL).get(language).getTitle(), template);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User verifyUser(String key) {
        User user = userRepository.findByEmail(verificationService.extractUserEmail(key));
        if (user == null || !verificationService.verifyAccount(user.getVerificationKey(), key)) {
            throw new ApiException(INVALID_VERIFICATION_KEY, HttpStatus.BAD_REQUEST);
        }
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(toUserRoles(Set.of(RoleType.USER.name())));
        user.setVerificationKey("");
        userRepository.save(user);
        return user;
    }

    private Set<Role> toUserRoles(Set<String> roles) {
        return roles.stream()
                .map(role -> roleRepository.findByRole(RoleType.valueOf(role)))
                .collect(Collectors.toSet());
    }
}
