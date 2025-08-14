package com.abbascoban.business.services.impl;

import com.abbascoban.business.services.IAuthServices;
import com.abbascoban.data.entity.RefreshToken;
import com.abbascoban.data.entity.User;
import com.abbascoban.data.repository.RefreshTokenRepository;
import com.abbascoban.data.repository.UserRepository;
import com.abbascoban.exception.BaseExcepiton;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.ErrorMessageType;
import com.abbascoban.jwt.AuthRequest;
import com.abbascoban.jwt.AuthResponse;
import com.abbascoban.jwt.JwtService;
import com.abbascoban.jwt.RefreshTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthServices {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;


    public RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken= new RefreshToken();
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    public boolean isTokenExpired(Date date){
        return new Date().before(date);
    }


    @Override
    public AuthResponse authenticate(AuthRequest request) {


            UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<User> optUser = userRepository.findByUsername(request.getUsername());

            String accessToken= jwtService.generateToken(optUser.get());
            RefreshToken refreshToken = createRefreshToken(optUser.get());

            //BU AŞAĞIDAKİ KODUN AMACI DB YE SOR BAKALIM BU KULLANICI DAHA ÖNCEDEN REFRESH TOKEN ALMIŞ MI ALDIYSA DOLU GELİCEK ALMADIYSA BOŞ GELİCEK
            // EĞER BOŞ GELİYORSA İLK DEFA authenticate OLMUŞ DEMEKTİR EĞER HİÇ İF BLOĞUNA GİRMEZ, EĞER DOLU GELİRSE ÖNCEDEN REFRESH TOKEN ALMIŞ DEMEKTİR
            Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser(optUser.get());
            if(optionalRefreshToken.isPresent()){
                optionalRefreshToken.get().setExpiredDate(refreshToken.getExpiredDate());
                optionalRefreshToken.get().setRefreshToken(refreshToken.getRefreshToken());
                RefreshToken updatedRefreshToken = refreshTokenRepository.save(optionalRefreshToken.get());
                return new AuthResponse(accessToken,updatedRefreshToken.getRefreshToken());
            }
            // BU KISIM KULLANICI İLK DEFA authenticate SERVİSİNİ KULLANIRSA ÇALIŞICAK YUKARDAKİ İF BLOĞUNA HİÇ GİRMİYCEK VE İLK REFRESH TOKENİNİ ALACAK
            RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);


            return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());


    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {

        Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if(optRefreshToken.isPresent()){

            if (!isTokenExpired(optRefreshToken.get().getExpiredDate())){
                throw  new BaseExcepiton(new ErrorMessage(ErrorMessageType.REFRESH_TOKEN_ERROR,""));
            }

            User user = optRefreshToken.get().getUser();

            String accessToken= jwtService.generateToken(user);
            RefreshToken refreshToken = createRefreshToken(user);
            // DB DEKİ REFRESH TOKENİ ÇETİKTEN SONRA SADECE AŞAĞIDAKİ 2 ALANI SETLEMEMİZ YETERLİ SONRA TEKRARDAN KAYDETTİĞİMİZDE UPDATE EDİLMİŞ OLACAK SADECE
            optRefreshToken.get().setRefreshToken(refreshToken.getRefreshToken());
            optRefreshToken.get().setExpiredDate(refreshToken.getExpiredDate());

            RefreshToken savedRefreshToken = refreshTokenRepository.save(optRefreshToken.get());


            return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());

        }
        throw new BaseExcepiton(new ErrorMessage(ErrorMessageType.REFRESH_TOKEN_ERROR,""));
    }
}
