package com.bookrental.bookrental.schedular;

import com.bookrental.bookrental.mapper.BookTransactionMapper;
import com.bookrental.bookrental.model.Book;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.repository.BookRepository;
import com.bookrental.bookrental.repository.MemberRepository;
import com.bookrental.bookrental.service.email.NewEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OverdueBookEmailScheduler {

    private final BookTransactionMapper bookTransactionMapper;
    private final NewEmailService emailService;

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    //    @Scheduled(cron = "0 * * * * *") // Run every minute
//    @Scheduled(cron = "0/1 * * * * *") // Run every second
    @Scheduled(cron = "0 0 0 * * *")
    public void sendOverdueEmails() {
        List<BookTransactionResponse> list = bookTransactionMapper.overdeuList();
        for (BookTransactionResponse bookTransaction : list) {

            int memberId = bookTransaction.getMemberId();
            int bookId = bookTransaction.getBookId();

            Book book = bookRepository.findById(bookId).get();

            Member m = memberRepository.findById(memberId).get();

            String subject = "Overdeu Book Reminder";

            String message = "Dear " + m.getName() + " ,\n\n" + " This is reminder that the book with the name " + book.getName()
                    + " is overdeu. Please return ASAP. \n\n Thank you! ";



            String emailMember = m.getEmail();
            try {
                emailService.sendEmail(emailMember, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
