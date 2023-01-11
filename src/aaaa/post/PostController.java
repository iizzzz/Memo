package com.server.seb41_main_11.post;

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
@RequestMapping("/posts")
@Validated
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper mapper;

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody PostDto.userPost post) {
        Post findPost = postService.create(mapper.postToEntity(post));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.entityToResponse(findPost)), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody PostDto.counselorPost post) {
        Post findPost = postService.create(mapper.postToEntity(post));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.entityToResponse(findPost)), HttpStatus.CREATED);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity patch(@PathVariable("post-id") @Positive long postId,
                                @Valid @RequestBody PostDto.Patch patch) {
        patch.setPostId(postId);

        Post update = postService.update(mapper.patchToEntity(patch));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.entityToResponse(update)), HttpStatus.OK);
    }
    
    @GetMapping("/{post-id}")
    public ResponseEntity get(@PathVariable("post-id") @Positive long postId) {
        Post Post = postService.find(postId);
        
        return new ResponseEntity(new SingleResponseDto<>(mapper.entityToResponse(Post)), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity getAll(@Positive @RequestParam(defaultValue = "1") int page,
                                 @Positive @RequestParam(defaultValue = "10") int size) {
        Page<Post> pagePost = postService.findAll(page-1, size);
        List<Post> posts = pagePost.getContent();
        
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.entitysToResponses(posts), pagePost), HttpStatus.OK);
    }
    
    @DeleteMapping("/{post-id}")
    public ResponseEntity delete(@PathVariable("post-id") @Positive long postId) {
        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
