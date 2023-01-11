package com.server.seb41_main_11.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "userId", target = "post.postId")
    Comment postToEntity(CommentDto.Post post);

    Comment patchToEntity(CommentDto.Patch patch);

    CommentDto.Response entityToResponse(Comment comment);

    List<CommentDto.Response> entitysToResponses(List<Comment> comments);
}
