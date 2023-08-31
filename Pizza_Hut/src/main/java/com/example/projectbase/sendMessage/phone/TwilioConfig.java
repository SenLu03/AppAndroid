//package com.example.projectbase.sendMessage.phone;
//
//import com.twilio.http.TwilioRestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Value;
//
//@Configuration
//public class TwilioConfig {
//
//    @Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Bean
//    public TwilioRestClient twilioRestClient() {
//        TwilioRestClient twilioRestClient = new TwilioRestClient.Builder(accountSid, authToken).build();
//        return twilioRestClient;
//    }
//}
//
//
