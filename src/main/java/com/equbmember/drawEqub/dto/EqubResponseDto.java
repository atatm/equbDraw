package com.equbmember.drawEqub.dto;

import com.equbmember.drawEqub.model.Enum.Interval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EqubResponseDto {
    private Interval equbType;
    private String equbName;
    private int numberOfMembers;
    private BigDecimal amount;
}
