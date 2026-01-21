package com.jpmc.midascore;
import com.jpmc.midascore.component.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;



class TaskTwoTests {

    private final FileLoader fileLoader = new FileLoader();

    @Test
    void task_two_verifier() {
        String[] transactionLines =
                fileLoader.loadStrings("test_data/poiuytrewq.txt");

        for (String line : transactionLines) {
            System.out.println("Transaction line = " + line);
        }
    }
}
