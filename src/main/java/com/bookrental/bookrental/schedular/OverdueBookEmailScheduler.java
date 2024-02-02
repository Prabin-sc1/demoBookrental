package com.bookrental.bookrental.schedular;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.mapper.BookTransactionMapper;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionExcelResponse;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.MemberRepository;
import com.bookrental.bookrental.service.book.BookService;
import com.bookrental.bookrental.service.email.NewEmailService;
import com.bookrental.bookrental.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OverdueBookEmailScheduler {

    private final BookTransactionMapper bookTransactionMapper;
    private final NewEmailService emailService;
    private final BookService bookService;

    private final MemberService memberService;


    //    @Scheduled(cron = "0 * * * * *") // Run every minute
//    @Scheduled(cron = "0/1 * * * * *") // Run every second
//    @Scheduled(cron = "0 0 0 * * *")
    public void sendOverdueEmails() {
        List<BookTransactionExcelResponse> list = bookTransactionMapper.overdeuList();
        for (BookTransactionExcelResponse bookTransaction : list) {

            int memberId = bookTransaction.getMemberId();
            int bookId = bookTransaction.getBookId();

            Book bookById = bookService.findBookById(bookId);
            Member memberById = memberService.findMemberById(memberId);


            String subject = "Overdue Book Reminder";

            String message = "Dear " + memberById.getName() + " ,\n\n" + " This is reminder that the book with the name " + bookById.getName()
                    + " is overdue. Please return ASAP. \n\n Thank you! ";
            String emailMember = memberById.getEmail();
            try {
                emailService.sendEmail(emailMember, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
