package com.bookrental.bookrental.service.excel;

import com.bookrental.bookrental.helpers.Helper;
import com.bookrental.bookrental.mapper.AuthorMapper;
import com.bookrental.bookrental.mapper.BookMapper;
import com.bookrental.bookrental.mapper.BookTransactionMapper;
import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.book.BookResponsePojo;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.repository.AuthorRepository;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.BookTransactionRepository;
import com.bookrental.bookrental.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {
//    public static String[] HEADERS = {"id", "name", "noOfPages", "isbn", "rating", "stockCount", "publishedDate", "photo"};


    public static String[] getHeaders(Class<?> clazz) {
        List<String> headers = new ArrayList<>();

        // Get all of the fields in the class
        Field[] fields = clazz.getDeclaredFields();

        // Iterate over the fields and add the field names to the list of headers
        for (Field field : fields) {
            headers.add(field.getName());
        }
        // Convert the list of headers to an array
        String[] headersArray = headers.toArray(new String[headers.size()]);
        return headersArray;
    }

    public static String SHEET_NAME = "book-transaction";

    private final AuthorRepository authorRepository;
    public ByteArrayInputStream getActualDataData() throws IOException {
        List<Author> all = authorRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(all, SHEET_NAME, getHeaders(Author.class));
        return byteArrayInputStream;
    }
}
