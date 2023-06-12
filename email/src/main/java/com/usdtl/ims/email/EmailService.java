package com.usdtl.ims.email;
import com.usdtl.ims.clients.DepartmentTransformedRequest;
import com.usdtl.ims.clients.RequestItemRequest;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private Configuration config;

    public void sendScheduledEmail(List<DepartmentTransformedRequest> request, Model model) throws MessagingException, IOException, TemplateException {
        Template template = config.getTemplate("scheduled-email-template.ftl");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model.addAttribute("requestItems", request));

        helper.setSubject("Scheduled Items");
        helper.setTo("u.batsaikhan@gmail.com");
        helper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    public void sendConfirmationEmail(List<RequestItemRequest> requestItems) throws IOException, MessagingException, TemplateException {

        Template template = config.getTemplate("confirmation-email-template.ftl");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

        Map<String, List<RequestItemRequest>> map = new HashMap<>();
        map.put("requestItems", requestItems);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        helper.setSubject("Scheduled Items");
        helper.setTo("u.batsaikhan@gmail.com");
        helper.setText(html, true);
        mailSender.send(mimeMessage);
    }
}