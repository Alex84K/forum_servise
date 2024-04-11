package ait.cohort34.accouting.dto.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrekt role")
public class IncorrectroleExeption extends RuntimeException {}
