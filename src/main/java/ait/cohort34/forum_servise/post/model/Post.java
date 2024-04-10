package ait.cohort34.forum_servise.post.model;

import ait.cohort34.forum_servise.post.dto.CommentDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Document(collection = "poosts")
public class Post {
    String id;
    @Setter
    String title;
    @Setter
    String content;
    @Setter
    String author;
    LocalDateTime dateCreated = LocalDateTime.now();
    Set<String> tags = new HashSet<>();
    int likes;
    List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, Set<String> tags, String author) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
    }

    public void addLike() {
        likes++;
    }

    public boolean addTag(String tag) {
        return  tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }

    public boolean removeComment(Comment comment) {
        return comments.remove(comment);
    }
}
