package com.jpmc.midascore;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import com.jpmc.midascore.component.KafkaProducer;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        topics = { "transactions" }
)
public class TaskThreeTests {

    static final Logger logger = LoggerFactory.getLogger(TaskThreeTests.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private FileLoader fileLoader;

    @Test
    void task_three_verifier() throws InterruptedException {

        // populate users
        userPopulator.populate();

        // load transactions
        String[] transactionLines =
                fileLoader.loadStrings("/test_data/mnbvcxz.vbnm");

        // send transactions
        for (String transactionLine : transactionLines) {
            kafkaProducer.send(transactionLine);
        }

        Thread.sleep(2000);

        logger.info("----------------------------------------------------------");
        logger.info("use your debugger to find out what waldorf's balance is");
        logger.info("kill this test once you find the answer");

        // INTENTIONAL INFINITE LOOP
        while (true) {
            Thread.sleep(20000);
            logger.info("...");
        }
    }
}