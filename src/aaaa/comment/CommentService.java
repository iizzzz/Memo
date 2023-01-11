package com.server.seb41_main_11.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    // ----------------- DI ---------------------
    private final CommentRepository commentRepository;

    // ----------------- DI ---------------------
    public Comment create(Comment comment) {
        verifyExistsComment(comment.getCommentId());

        return commentRepository.save(comment);
    }

    public Comment update(Comment comment) {
        return null;
    }

    @Transactional(readOnly = true)
    public Comment find(long commentId) {
        return findVerifiedcomment(commentId);
    }

    @Transactional(readOnly = true)
    public Page<Comment> findAll(int page, int size) {
        Page<Comment> comments = commentRepository.findAll(PageRequest.of(page, size, Sort.by("commentId").descending()));
        return comments;
    }

    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment findVerifiedcomment(long commentId) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Comment findComment = optComment.orElseThrow(EntityNotFoundException::new);

        return findComment;
    }

    private void verifyExistsComment(long commentId) {
        Optional<Comment> comment = Optional.of(commentRepository.getReferenceById(commentId));

        throw new EntityNotFoundException("유저를 찾을 수 없습니다.");
    }
}
