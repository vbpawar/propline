package com.freelancer.RealEstate.utils;

import com.freelancer.RealEstate.Constants.PropertyConstant;
import com.freelancer.RealEstate.config.EmailConfig;
import com.freelancer.RealEstate.model.EmailData;
import com.freelancer.RealEstate.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailService {


    @Autowired
    private EmailConfig emailConfig;

    public ResponseDto sendEmail(EmailData emailData) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Properties props = propertyConf();
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setSubject(emailConfig.getSubject());
            message.setSentDate(new Date());
            String bodyText = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table {\n" +
                    "  font-family: arial, sans-serif;\n" +
                    "  border-collapse: collapse;\n" +
                    "  width: 100%;\n" +
                    "}\n" +
                    "\n" +
                    "td, th {\n" +
                    "  border: 1px solid #dddddd;\n" +
                    "  text-align: left;\n" +
                    "  padding: 8px;\n" +
                    "}\n" +
                    "\n" +
                    "tr:nth-child(even) {\n" +
                    "  background-color: #dddddd;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<table>\n" +
                    "  <tr>\n" +
                    "    <th>Name</th>\n" +
                    "    <th>Email</th>\n" +
                    "    <th>Phone</th>\n" +
                    "    <th>Message</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "  \t<td>" + emailData.getName() + "</td>\n" +
                    "    <td>" + emailData.getEmail() + "</td>\n" +
                    "    <td>" + emailData.getPhone() + "</td>\n" +
                    "    <td>" + emailData.getMessage() + "</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n" +
                    "\n";
            if (message != null) {
                InternetAddress[] mailAddress_TO = {new InternetAddress(emailConfig.getAddress())};
                message.addRecipients(Message.RecipientType.TO, mailAddress_TO);
            }

            message.setContent(bodyText, "text/html");
            Transport.send(message);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }

    private Properties propertyConf() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", emailConfig.getHost());
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    public ResponseDto sendDataToAgent(EmailData emailData,String agentEmail) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Properties props = propertyConf();
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setSubject(emailConfig.getSubject());
            message.setSentDate(new Date());
            String bodyText = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table {\n" +
                    "  font-family: arial, sans-serif;\n" +
                    "  border-collapse: collapse;\n" +
                    "  width: 100%;\n" +
                    "}\n" +
                    "\n" +
                    "td, th {\n" +
                    "  border: 1px solid #dddddd;\n" +
                    "  text-align: left;\n" +
                    "  padding: 8px;\n" +
                    "}\n" +
                    "\n" +
                    "tr:nth-child(even) {\n" +
                    "  background-color: #dddddd;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<table>\n" +
                    "  <tr>\n" +
                    "    <th>Name</th>\n" +
                    "    <th>Email</th>\n" +
                    "    <th>Phone</th>\n" +
                    "    <th>Message</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "  \t<td>" + emailData.getName() + "</td>\n" +
                    "    <td>" + emailData.getEmail() + "</td>\n" +
                    "    <td>" + emailData.getPhone() + "</td>\n" +
                    "    <td>" + emailData.getMessage() + "</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n" +
                    "\n";
            if (message != null) {
                InternetAddress[] mailAddress_TO = {new InternetAddress(agentEmail)};
                message.addRecipients(Message.RecipientType.TO, mailAddress_TO);
            }

            message.setContent(bodyText, "text/html");
            Transport.send(message);
            responseDto.setMessage(PropertyConstant.SUCCESS_MSG);
            responseDto.setStatusCode(PropertyConstant.SUCCESS_STATUS_CODE);
        } catch (Exception e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setStatusCode(PropertyConstant.FAILED_STATUS_CODE);
        }
        return responseDto;
    }
}
