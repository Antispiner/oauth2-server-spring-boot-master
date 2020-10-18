package com.internalproject.api.controllers;

import com.internalproject.api.components.converters.impl.UserConverterImpl;
import com.internalproject.api.domain.dto.NotificationDTO;
import com.internalproject.api.domain.dto.UserDto;
import com.internalproject.api.domain.requests.UserCreateRequest;
import com.internalproject.api.enums.Language;
import com.internalproject.api.model.entity.user.User;
import com.internalproject.api.security.models.PasswordTokenGranter;
import com.internalproject.api.services.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Api(value = "User rest controller")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserConverterImpl userConverter;


    private static final String MSG_FIELD_ATTRIBUTE = "messageField";
    private static final String MSG_VERIFICATION_SUCCESS = "User successfully activated";
    private static final String MSG_VERIFICATION_FAIL = "Verification key is invalid";
    private static final String STATUS_REFERENCE = "statusRef";
    private static final String LANGUAGE = "language";

    @Autowired
    public UserController(UserServiceImpl userService, UserConverterImpl userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @Autowired
    TokenEndpoint tokenEndPoint;

    @ApiOperation(value = "Endpoint to create users.", response = NotificationDTO.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User was successfully created."),
            @ApiResponse(code = 400, message = "Unable to retrieve users list, bad request."),
    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserCreateRequest request) {
        User createdUser = userService.create(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ModelAndView verifyUser(@ApiParam(name = "key", value = "verification key", required = true) @RequestParam("key") String key, @RequestParam("language") String language) {
        ModelAndView modelAndView = new ModelAndView("verify");
        Language lang = Language.RU;
        try {
            userService.verifyUser(key);
            modelAndView.addObject(LANGUAGE, lang.name());
            modelAndView.addObject(STATUS_REFERENCE, true);
            modelAndView.addObject(MSG_FIELD_ATTRIBUTE, MSG_VERIFICATION_SUCCESS);
        } catch (Exception e) {
            modelAndView.addObject(LANGUAGE, lang.name());
            modelAndView.addObject(STATUS_REFERENCE, false);
            modelAndView.addObject(MSG_FIELD_ATTRIBUTE, MSG_VERIFICATION_FAIL);
        }
        return modelAndView;
    }
}
