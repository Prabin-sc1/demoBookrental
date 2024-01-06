package com.bookrental.bookrental.service.author;

import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.exception.CategoryAlreadyExistsException;
import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.pojo.author.AuthorRequestPojo;
import com.bookrental.bookrental.pojo.author.AuthorResponsePojo;
import com.bookrental.bookrental.repository.AuthorRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    private final CustomMessageSource customMessageSource;

    private final ModelMapper modelMapper;

    @Override
    public void createUpdateAuthor(AuthorRequestPojo authorRequestPojo) {
        Author author = new Author();
        if (authorRequestPojo.getId() != null) {
            author = authorRepository.findById(authorRequestPojo.getId()).orElse(author);
        }

        try {
            beanUtils.copyProperties(author, authorRequestPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }
        try {
            authorRepository.save(author);
        } catch (DataIntegrityViolationException e) {
            throw new CategoryAlreadyExistsException("A author with the email " + author.getEmail() + " already exists.");
        }

    }

    @Override
    public List<AuthorResponsePojo> getAllAuthor() {
        List<Author> a = authorRepository.findAll();
        List<AuthorResponsePojo> list1 = a.stream().map(e -> this.modelMapper.map(e, AuthorResponsePojo.class)).collect(Collectors.toList());
        return list1;
    }

    @Override
    public AuthorResponsePojo getAuthorById(Integer id) {
        Author a = authorRepository.findById(id).orElseThrow(() -> new AppException(customMessageSource.
                get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.AUTHOR)));
        return this.modelMapper.map(a, AuthorResponsePojo.class);
    }

    @Override
    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }
}
