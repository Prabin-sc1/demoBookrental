package com.bookrental.bookrental.service.excel;

import com.bookrental.bookrental.helpers.Helper;
import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.repository.BookTransactionRepository;
import com.bookrental.bookrental.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final BookTransactionRepository bookTransactionRepository;

    public ByteArrayInputStream getActualDataData() throws IOException {
        List<BookTransaction> all = bookTransactionRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(all);
        return byteArrayInputStream;
    }

}
