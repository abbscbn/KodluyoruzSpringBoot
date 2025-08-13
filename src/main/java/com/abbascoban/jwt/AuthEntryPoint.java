package com.abbascoban.jwt;

import com.abbascoban.exception.BaseExcepiton;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.ErrorMessageType;
import com.abbascoban.rootentity.RootEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@Component
public class AuthEntryPoint  implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //BURADA TOKEN ÇÖZÜLÜRKEN ZAMAN AŞIMINA UĞRAMA VE GENEL TOKEN İLE İLGİLİ HATALARI JSON FORMATINDA DÖNDÜRÜYORUZ
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        RootEntity<String> error = RootEntity.error(authException.getMessage());
        String json = objectMapper.writeValueAsString(error);
        response.getWriter().write(json);

       // response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());


    }
}
