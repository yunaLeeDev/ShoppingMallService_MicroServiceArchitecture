package com.example.userservice.dto;

import com.example.userservice.vo.ResponseOrder;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
        private String email;
        private String pwd;
        private String name;
        private String userId;
        private String createdAt;

        private String encryptedPwd;

        public void setOrders(List<ResponseOrder> orderList) {
        }
}
