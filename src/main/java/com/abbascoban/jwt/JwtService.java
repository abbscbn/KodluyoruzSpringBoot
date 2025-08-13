package com.abbascoban.jwt;

import com.abbascoban.data.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {

    private static final String SECRET_KEY="tXWwnKEgE+xM9VdrCffxO7dmhH9MD+aWB0v69LxkwUE=";

    public String generateToken(UserDetails userDetails){
        Map<String,Object> map= new HashMap<>();

        if(userDetails.getUsername().equals("admin")){
            map.put("ROLE","ADMİN");
        }
        else{
            map.put("ROLE","USER");
        }


      return   Jwts.builder().
                setSubject(userDetails.getUsername())
                    .addClaims(map)
                        .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis()+(1000*60*60*2)))
                                        .signWith(getKey(), SignatureAlgorithm.HS256).compact();

    }



    public  Object getRoleByToken(String token){
        Claims claims = getClaims(token);
        return claims.get("ROLE");
    }

    public  Claims getClaims(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public <T> T exportToken(String token, Function<Claims,T> claimsTFunction){
        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);
    }


    public String getUsernameByToken(String token){

         return exportToken(token, Claims::getSubject);

    }

    public boolean isTokenExpired(String token){
        Date expiredDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }

    public  Key getKey(){
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

}
