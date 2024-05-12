package com.sapo.salemanagement.services.auth;

import com.sapo.salemanagement.dto.auth.AuthDto;
import com.sapo.salemanagement.dto.auth.AuthResponse;
import com.sapo.salemanagement.dto.auth.UserInfoDto;
import com.sapo.salemanagement.dto.auth.VerifyTokenRequest;
import com.sapo.salemanagement.exceptions.NotFoundException;
import com.sapo.salemanagement.exceptions.UnAuthorizedException;
import com.sapo.salemanagement.models.UserEntity;
import com.sapo.salemanagement.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authenticate(AuthDto authDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDto.getPhone(),
                        authDto.getPassword()
                )
        );
        String token = jwtService.generateToken(authentication.getName());
        UserEntity userEntity = userRepository.findByPhone(authDto.getPhone())
                .orElseThrow(() -> new NotFoundException("profile not found"));
        ModelMapper modelMapper = new ModelMapper();
        return AuthResponse.builder()
                .userInfoDto(modelMapper.map(userEntity, UserInfoDto.class))
                .token(token)
                .build();
    }

    public AuthResponse register(AuthDto authDto) {
//        UserEntity userEntity = userRepository.findByPhone(authDto.getPhone())
//                .orElseThrow(() -> new NotFoundException("profile not found"));

        UserEntity userEntity = null;
        Optional<UserEntity> existingUserOptional = userRepository.findByPhone(authDto.getPhone());

        if (existingUserOptional.isPresent()) {
            throw new NotFoundException("Phone exist");
        } else {
            userEntity = new UserEntity();
            userEntity.setPhone(authDto.getPhone());
            userEntity.setPassword(passwordEncoder.encode(authDto.getPassword()));
            userEntity.setFullName("Nhân viên mới");
            userRepository.save(userEntity);
        }

        var jwtToken = jwtService.generateToken(userEntity.getPhone());
        ModelMapper modelMapper = new ModelMapper();
        return AuthResponse.builder()
                .userInfoDto(modelMapper.map(userEntity, UserInfoDto.class))
                .token(jwtToken)
                .build();
    }

    public UserInfoDto verifyToken(VerifyTokenRequest request) {
        String phone = jwtService.extractUsername(request.getToken());
        if (!jwtService.isTokenValid(request.getToken(), phone)) {
            throw new UnAuthorizedException();
        }

        UserEntity userEntity = userRepository.findByPhone(phone)
                .orElseThrow(() -> new NotFoundException("profile not found"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, UserInfoDto.class);
    }
}
