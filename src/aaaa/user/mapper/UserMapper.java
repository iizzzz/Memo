package com.server.seb41_main_11.user.mapper;

import com.server.seb41_main_11.user.dto.UserDto;
import com.server.seb41_main_11.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User postToEntity(UserDto.Post post);
    User patchToEntity(UserDto.Patch patch);
    UserDto.Response entityToResponse(User user);
    List<UserDto.Response> entitysToResponses(List<User> users);
}
