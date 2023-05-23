package io.sannette.eis.mega.customer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {

    private String command;  // APPROVE, REJECT, REVIEWED1, REVIEWED2, ONBOARDED
    private String message;

}
