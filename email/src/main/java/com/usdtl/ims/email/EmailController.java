package com.usdtl.ims.email;
import com.usdtl.ims.clients.departmentMasterClients.ExtractionsMasterClient;
import com.usdtl.ims.clients.departmentMasterClients.common.response.DepartmentMasterResponse;
import com.usdtl.ims.clients.responseRecord.RequestItemRequest;
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
    private ExtractionsMasterClient extractionsMasterClient;

    @GetMapping("scheduled-email")
    public List<DepartmentMasterResponse> sendScheduledEmail(Model model) throws MessagingException, TemplateException, IOException {
        service.sendScheduledEmail(extractionsMasterClient.getEmailItems(), model);
        return extractionsMasterClient.getEmailItems();
    }

    @PostMapping("confirmation-email")
    public void sendConfirmationEmail(@RequestBody List<RequestItemRequest> request) throws MessagingException, TemplateException, IOException {
        service.sendConfirmationEmail(request);
    }
}
