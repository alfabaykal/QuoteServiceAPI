package com.alfabaykal.QuoteServiceAPI.util;

import com.alfabaykal.QuoteServiceAPI.dto.*;
import com.alfabaykal.QuoteServiceAPI.model.Quote;
import com.alfabaykal.QuoteServiceAPI.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {
    private final ModelMapper modelMapper;

    public EntityDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(User.class, UserRegistrationDto.class);
        modelMapper.createTypeMap(UserRegistrationDto.class, User.class);

        modelMapper.createTypeMap(User.class, UserDto.class);
        modelMapper.createTypeMap(UserDto.class, User.class);

        modelMapper.createTypeMap(Quote.class, AddQuoteDto.class);
        modelMapper.createTypeMap(AddQuoteDto.class, Quote.class);

        modelMapper.createTypeMap(Quote.class, GetQuoteDto.class);
    }

    public User convertUserRegistrationDtoToUser(UserRegistrationDto userRegistrationDto) {
        return modelMapper.map(userRegistrationDto, User.class);
    }

    public User convertUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto converUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public Quote convertAddQuoteDtoToQuote(AddQuoteDto addQuoteDto) {
        return modelMapper.map(addQuoteDto, Quote.class);
    }

    public GetQuoteDto convertQuoteToGetQuoteDto(Quote quote) {
        return modelMapper.map(quote, GetQuoteDto.class);
    }

    public Quote convertUpdateQuoteDtoToQuote(UpdateQuoteDto updateQuoteDto) {
        return modelMapper.map(updateQuoteDto, Quote.class);
    }
}
