package me.danhthanhf.distant_class.mapper;

import me.danhthanhf.distant_class.dto.auth.RegisterRequest;
import me.danhthanhf.distant_class.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
}
