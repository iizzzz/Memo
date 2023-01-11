package com.server.seb41_main_11.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "userId", target = "user.userId")
    Post postToEntity(PostDto.userPost post);

    @Mapping(source = "counselorId", target = "counselor.counselorId")
    Post postToEntity(PostDto.counselorPost post);

    Post patchToEntity(PostDto.Patch patch);

    PostDto.Response entityToResponse(Post post);

    List<PostDto.Response> entitysToResponses(List<Post> posts);
}
