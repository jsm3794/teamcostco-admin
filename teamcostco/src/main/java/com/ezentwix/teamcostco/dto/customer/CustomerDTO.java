package com.ezentwix.teamcostco.dto.customer;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDTO {
    private Integer customer_id;
    private String nickname;
    private String email;
    private String phone_number;
    private Date date_of_birth;
    private String gender;
    private String status;
    private Date create_at;
    private Date update_at;
    private String social_provider;
    private String social_id;
    private Integer default_address_id; 

}
