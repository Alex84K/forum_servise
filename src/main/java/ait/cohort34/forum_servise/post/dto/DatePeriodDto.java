package ait.cohort34.forum_servise.post.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DatePeriodDto {
    LocalDate dateFrom;
    LocalDate dateTo;
}