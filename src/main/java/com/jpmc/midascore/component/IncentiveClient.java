package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.foundation.Incentive;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IncentiveClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String INCENTIVE_URL =
            "http://localhost:8080/incentive";

    public Incentive getIncentive(Transaction transaction) {
        return restTemplate.postForObject(
                INCENTIVE_URL,
                transaction,
                Incentive.class
        );
    }
}