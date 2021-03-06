package com.example.demo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class OauthController {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

//    @GetMapping("/oauth2/callback")
//    public OauthTokenDto callback(@RequestParam String code, HttpServletRequest request) throws Exception {
//        System.out.println("code = " + code);
//        OauthTokenDto token = getToken(code);
//        System.out.println("getToken() = " + token);
//
//        return token;
//    }

    @GetMapping("/oauth2/callback")
    public String callback(@RequestParam String code) throws JsonProcessingException {
        System.out.println("code = " + code);

        OauthTokenDto token = getToken(code);
        System.out.println("getToken() = " + token);

        assert token != null;
        return token.toString();
    }

    private OauthTokenDto getToken(String code) throws JsonProcessingException {
        String credentials = "testClientId:testSecret";
        String encodedCredentials = new String(Base64.encode(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("response.getBody() = " + response.getBody());
            return objectMapper.readValue(response.getBody(), OauthTokenDto.class);
        }
        return null;
    }

    @GetMapping("/api/me")
    public String apiMe(Principal principal) {
        System.out.println("principal = " + principal);

        if(principal == null) {
            return "pricipal is null";
        }
        return principal.getName();
    }

    @Getter
    @ToString
    static class OauthTokenDto {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private long expires_in;
        private String scope;
    }
}
