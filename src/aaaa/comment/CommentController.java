package com.server.seb41_main_11.comment;

import com.server.seb41_main_11.globalresponse.MultiResponseDto;
import com.server.seb41_main_11.globalresponse.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Validated
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody CommentDto.Post post) {
        Comment comment = commentService.create(mapper.postToEntity(post));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.entityToResponse(comment)), HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patch(@PathVariable("comment-id") @Positive long commentId,
                                @Valid @RequestBody CommentDto.Patch patch) {
        patch.setCommentId(commentId);

        Comment update = commentService.update(mapper.patchToEntity(patch));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.entityToResponse(update)), HttpStatus.OK);
    }
    
    @GetMapping("/{comment-id}")
    public ResponseEntity get(@PathVariable("comment-id") @Positive long commentId) {
        Comment comment = commentService.find(commentId);
        
        return new ResponseEntity(new SingleResponseDto<>(mapper.entityToResponse(comment)), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity getAll(@Positive @RequestParam(defaultValue = "1") int page,
                                 @Positive @RequestParam(defaultValue = "10") int size) {
        Page<Comment> pagecomment = commentService.findAll(page-1, size);
        List<Comment> comments = pagecomment.getContent();
        
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.entitysToResponses(comments), pagecomment), HttpStatus.OK);
    }
    
    @DeleteMapping("/{comment-id}")
    public ResponseEntity delete(@PathVariable("comment-id") @Positive long commentId) {
        commentService.delete(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
