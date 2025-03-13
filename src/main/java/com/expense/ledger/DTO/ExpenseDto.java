package com.expense.ledger.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {
     private String externalId;

     @JsonProperty(value = "amount")
     private String amount;

     @JsonProperty(value = "user_id")
     private String userId;

     @JsonProperty(value = "merchant")
     private String merchant;

     @JsonProperty(value = "currency")
     private String currency;

     @JsonProperty(value = "created_at")
     private String createdAt;

     public ExpenseDto(String json) {
          try {
               ObjectMapper mapper = new ObjectMapper();
               mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
               ExpenseDto expense = mapper.readValue(json, ExpenseDto.class);
               this.externalId = expense.externalId;
               this.amount = expense.amount;
               this.userId = expense.userId;
               this.merchant = expense.merchant;
               this.currency = expense.currency;
               this.createdAt = expense.createdAt;
          } catch (Exception e) {
               throw new RuntimeException("Failed to deserialize ExpenseDto from JSON", e);
          }
     }

}
