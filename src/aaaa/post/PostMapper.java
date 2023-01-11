package com.server.seb41_main_11.domain.post;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "kinds", target = "kinds")
    Post postToEntity(PostDto.userPost post);

    @Mapping(source = "counselorId", target = "counselor.counselorId")
    @Mapping(source = "kinds", target = "kinds")
    Post postToEntity(PostDto.counselorPost post);

    @Mapping(source = "kinds", target = "kinds")
    Post patchToEntity(PostDto.Patch patch);

    @Mapping(source = "kinds", target = "kinds")
    PostDto.Response entityToResponse(Post post);

    List<PostDto.Response> entitysToResponses(List<Post> posts);
}
