package com.ezentwix.teamcostco.dto.shipping;

import java.util.Date;

import lombok.Data;

@Data
public class ShippingDTO {
    
    private Integer address_id;
    private Integer customer_id;
    private Integer sales_id;
    private String recipient_name;
    private String road_address;
    private String lot_number_address;
    private String detail_address;
    private String postal_code;
    private String recipient_phone_number;
    private String sender_name;
    private String sender_phone_number;
    private String invoice_number;
    private String shipping_status;
    private String shipping_request_message;
    private Date created_at;
    private Date updated_at;
}
