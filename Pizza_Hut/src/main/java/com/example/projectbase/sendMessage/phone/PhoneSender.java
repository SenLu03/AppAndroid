//package com.example.projectbase.sendMessage.phone;
//
//import com.twilio.http.TwilioRestClient;
//import com.twilio.rest.api.v2010.account.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.twilio.rest.api.v2010.account.MessageCreator;
//import com.twilio.type.PhoneNumber;
//import com.twilio.exception.ApiException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PhoneSender {
//
//    @Autowired
//    private TwilioRestClient twilioRestClient;
//
//    public void sendSMS( String message) {
//        MessageCreator messageCreator = Message.creator(
//                new PhoneNumber("+84962618566"), // Số điện thoại người nhận
//                new PhoneNumber("+15416157139"), // Số điện thoại Twilio của bạn
//                message);
//
//        try {
//            Message sentMessage = messageCreator.create(twilioRestClient);
//            System.out.println("Gửi tin nhắn thành công! SID: " + sentMessage.getSid());
//        } catch (ApiException e) {
//            System.out.println("Lỗi khi gửi tin nhắn: " + e.getMessage());
//        }
//    }
//}
