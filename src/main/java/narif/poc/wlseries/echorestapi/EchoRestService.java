package narif.poc.wlseries.echorestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoRestService {

    @GetMapping("ping")
    public String ping(){
        return "pong";
    }
}
