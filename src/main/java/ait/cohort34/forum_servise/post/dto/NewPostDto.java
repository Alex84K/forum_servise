package ait.cohort34.forum_servise.post.dto;
import lombok.Getter;

import java.util.Set;

@Getter
public class NewPostDto {
    String title;
    String content;
    Set<String> tags;
}

