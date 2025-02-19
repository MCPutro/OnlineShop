package com.example.analyticservices.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportReqDto {
    private LocalDate startDate;
    private LocalDate endDate;
}
