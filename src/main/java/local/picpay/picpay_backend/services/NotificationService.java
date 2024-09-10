package local.picpay.picpay_backend.services;

import local.picpay.picpay_backend.domain.user.User;
import local.picpay.picpay_backend.dto.NotificationDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

      //  if (!(notificationResponse.getStatusCode() == HttpStatus.OK)){
      //      System.out.println("Error sending notification");
      //      throw new Exception("Notify service is down!");
//}

        System.out.println("Notificaçao enviada");
    }
}
