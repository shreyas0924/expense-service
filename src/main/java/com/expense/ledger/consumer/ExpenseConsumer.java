package com.expense.ledger.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.expense.ledger.DTO.ExpenseDto;
import com.expense.ledger.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseConsumer {
     
     private ExpenseService expenseService;

     @Autowired
     ExpenseConsumer(ExpenseService expenseService) {
          this.expenseService = expenseService;
     }

      @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
     public void listen(ExpenseDto eventData) {
          try {
               // Todo: Make it transactional, to handle idempotency and validate email,
               expenseService.createExpense(eventData);
          } catch (Exception ex) {
               ex.printStackTrace();
               System.out.println("ExpenseServiceConsumer: Exception is thrown while consuming kafka event");
          }
     }


}
