package ait.cohort34.forum_servise.post.dao;

import ait.cohort34.forum_servise.post.dto.PostDto;
import ait.cohort34.forum_servise.post.model.Post;

import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, String> {
    Stream<Post> findPostByAuthorIgnoreCase(String author);
    Stream<Post> findByTagsInIgnoreCase(Set<String> tags);

    Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
}
