package local.picpay.picpay_backend.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long recieverId) {
}
