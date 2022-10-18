package com.example.freemarket.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebhookNotification {
    public class WebhookData { 
        private Long id; 

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private Long id;
    private boolean live_mode;
    private String type;
    private String date_created;
    private Long user_id;
    private Long version;
    private String api_version;
    private String action;
    private WebhookData data;
}
