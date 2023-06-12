package com.usdtl.ims.departmentMaster.email;

import com.usdtl.ims.clients.DepartmentMasterClient;
import com.usdtl.ims.clients.DepartmentTransformedRequest;
import com.usdtl.ims.clients.RequestItemRequest;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("send")
@AllArgsConstructor
public class EmailController {

    private EmailService service;
    private DepartmentMasterClient transformedClient;

    @GetMapping("scheduled-email")
    public List<DepartmentTransformedRequest> sendScheduledEmail(Model model) throws MessagingException, TemplateException, IOException {
        service.sendScheduledEmail(transformedClient.getScheduledEmailItems(), model);
        return transformedClient.getScheduledEmailItems();
    }

    @PostMapping("confirmation-email")
    public void sendConfirmationEmail(@RequestBody List<RequestItemRequest> request) throws MessagingException, TemplateException, IOException {
        service.sendConfirmationEmail(request);
    }


}
