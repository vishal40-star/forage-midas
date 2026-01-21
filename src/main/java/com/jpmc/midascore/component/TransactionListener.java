package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private final UserRepository userRepository;
    private final IncentiveClient incentiveClient;

    public TransactionListener(
            UserRepository userRepository,
            IncentiveClient incentiveClient
    ) {
        this.userRepository = userRepository;
        this.incentiveClient = incentiveClient;
    }

   @KafkaListener(
        topics = "transactions",
        groupId = "midas-core-group"
)
public void listen(String message) {

    String[] parts = message.split(",");

    long senderId = Long.parseLong(parts[0]);
    long recipientId = Long.parseLong(parts[1]);
    float amount = Float.parseFloat(parts[2]);

    Transaction transaction =
            new Transaction(senderId, recipientId, amount);

    Incentive incentive = incentiveClient.getIncentive(transaction);
    float incentiveAmount = incentive.getAmount();

   UserRecord sender = userRepository.findById(senderId);
UserRecord recipient = userRepository.findById(recipientId);
    
sender.setBalance(sender.getBalance() - amount);
    recipient.setBalance(recipient.getBalance() + amount + incentiveAmount);

    userRepository.save(sender);
    userRepository.save(recipient);
}
}