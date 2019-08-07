package club.mydlq.springbootkafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = {"test"},groupId = "group1", containerFactory="kafkaListenerContainerFactory")
    public void kafkaListener(String message){
        System.out.println(message);
    }

}