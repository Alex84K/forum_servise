package ait.cohort34.forum_servise.post.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    String id;
    String title;
    String content;
    String author;
    LocalDateTime dateCreated;
    @Singular
    Set<String> tags;
    Integer likes;
    @Singular
    List<CommentDto> comments;

}