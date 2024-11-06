package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PaymentDTO {
    private String pay_id;
    private String pay_date;
    private double pay_amount;
    private String status;
}
