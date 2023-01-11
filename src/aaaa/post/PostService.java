package com.server.seb41_main_11.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post find(long postId) {
        return postRepository.getReferenceById(postId);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(int page, int size) {
        Page<Post> posts = postRepository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));
        return posts;
    }

    public void delete(long postId) {
        postRepository.deleteById(postId);
    }

    private Post findVerifiedPost(long postId) {
        Optional<Post> optPost = Optional.of(postRepository.getReferenceById(postId));
        return optPost.orElseThrow(() -> new EntityNotFoundException("글이 없습니다"));
    }
}
