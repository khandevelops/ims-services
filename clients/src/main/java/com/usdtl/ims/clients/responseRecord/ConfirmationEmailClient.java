package com.usdtl.ims.clients.responseRecord;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "confirmation-email")
public interface ConfirmationEmailClient {
    @PostMapping("/ims/api/v1/email/send/confirmation-email")
    public List<RequestItemRequest> sendConfirmationEmail(List<RequestItemRequest> requests);
}
