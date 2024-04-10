package ait.cohort34.forum_servise.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    String user;
    String message;
    LocalDateTime dataCreated;
    Integer likes;
}
