package com.bookrental.bookrental.schedular;

import com.bookrental.bookrental.mapper.BookTransactionMapper;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
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

    //    @Scheduled(cron = "0 * * * * *") // Run every minute
//    @Scheduled(cron = "0/1 * * * * *") // Run every second
    @Scheduled(cron = "0 0 0 * * *")
    public void sendOverdueEmails() {
        List<BookTransactionResponse> list = bookTransactionMapper.overdeuList();
//        list.stream().map(e -> {})
        for (BookTransactionResponse bookTransaction : list) {

            int b = bookTransaction.getMemberId();
            Member m = memberRepository.findById(b).get();

            String subject = "Overdeu Book Reminder";

            String message = "Dear " + bookTransaction.getMemberId() + " ,\n\n" + " This is reminder that the book with the code " + bookTransaction.getBookId()
                    + " is overdeu. Please return ASAP. ";
            String emailMember = m.getEmail();
            try {
                emailService.sendEmail(emailMember, subject, message);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

//    public void triggerOverdueEmailsManually() {
//        sendOverdueEmails(); // Call the method manually
//    }
}
