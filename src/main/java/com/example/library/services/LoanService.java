package com.example.library.services;

import com.example.library.controllers.requests.LoanRequest;
import com.example.library.controllers.requests.UpdateLoanRequest;
import com.example.library.models.BookModel;
import com.example.library.models.LoanModel;
import com.example.library.models.LoanOption;
import com.example.library.models.MemberModel;
import com.example.library.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final BookService bookService;

    public LoanService(@Autowired LoanRepository loanRepository, @Autowired MemberService memberService,
                       @Autowired BookService bookService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public LoanModel createLoan(LoanRequest loanRequest) { //exceptions
        BookModel book = bookService.readOne(loanRequest.getBookId());
        MemberModel member = memberService.readOne(loanRequest.getMemberId());

        if (loanRequest.getLoanOption().equals(LoanOption.RETURN)) {
            if(!doesMemberHaveBook(book, member)){
                throw new IllegalArgumentException("Member does not have that book!");
            }
            member.setNumberOfBooksLoaned(member.getNumberOfBooksLoaned() - 1);
            book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        } else {
            if(doesMemberHaveBook(book, member)){
                throw new IllegalArgumentException("Member already has that book!");
            }
            if (member.getNumberOfBooksLoaned() > 2) {
                throw new IllegalArgumentException("Member already has 3 or more books loaned!");
            }
            if (book.getCopiesAvailable() < 1) {
                throw new IllegalArgumentException("There are no copies of that book left!");
            }
            member.setNumberOfBooksLoaned(member.getNumberOfBooksLoaned() + 1);
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        }
        LoanModel loanModel = new LoanModel();
        loanModel.setLoanOption(loanRequest.getLoanOption());
        loanModel.setBook(book);
        loanModel.setMember(member);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        loanModel.setTimestamp(timestamp);
        member.addLoan(loanModel);
        book.addLoan(loanModel);
        try {
            return loanRepository.save(loanModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    private boolean doesMemberHaveBook(BookModel bookModel, MemberModel memberModel){
        long loansForBookAndMember;
        try{
            loansForBookAndMember= loanRepository.countByBookAndMember(bookModel,memberModel);
        }
        catch (Exception e){
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
        return loansForBookAndMember % 2 != 0;
    }

    public LoanModel readOne(long loanId) {
        return loanRepository.findById(loanId).orElseThrow(() ->
                new IllegalArgumentException("No book with that ID"));
    }

    public List<LoanModel> readAll() {
        try {
            return (List<LoanModel>) loanRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public long deleteLoan(long loanId) { //what to return
        LoanModel loan = readOne(loanId);
        BookModel book = loan.getBook();
        MemberModel member = loan.getMember();
        book.removeLoan(loan);
        member.removeLoan(loan);
        if (loan.getLoanOption().equals(LoanOption.LOAN)){
            member.setNumberOfBooksLoaned(member.getNumberOfBooksLoaned() - 1);
            book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        }
        else{
            member.setNumberOfBooksLoaned(member.getNumberOfBooksLoaned() + 1);
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        }
        try {
            loanRepository.deleteById(loanId);
        } catch (Exception e) {
            //exception
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
        return loanId;
    }

    //BAD METHOD, NEEDS WORK!!!
    public LoanModel updateLoan(UpdateLoanRequest updateLoanRequest) {
        LoanModel loanModel = readOne(updateLoanRequest.getId());

        BookModel oldBook = loanModel.getBook();
        MemberModel oldMember = loanModel.getMember();
        BookModel newBook = bookService.readOne(updateLoanRequest.getBookId());
        MemberModel newMember = memberService.readOne(updateLoanRequest.getMemberId());

        if(newBook.equals(oldBook) || newMember.equals(oldMember)){
            throw new IllegalArgumentException("CANNOT UPDATE WITH SAME MEMBER OR SAME BOOK - delete and make " +
                    "new loan request");
        }

        if (updateLoanRequest.getLoanOption().equals(LoanOption.RETURN)) {
            if(!doesMemberHaveBook(newBook, newMember)){
                throw new IllegalArgumentException("Member Does Not Have that Book");
            }
            newMember.setNumberOfBooksLoaned(newMember.getNumberOfBooksLoaned() - 1);
            newBook.setCopiesAvailable(newBook.getCopiesAvailable() + 1);
        } else {
            if(doesMemberHaveBook(newBook, newMember)){
                throw new IllegalArgumentException("Member Already Has that book!");
            }
            if (newMember.getNumberOfBooksLoaned() > 2) {
                throw new IllegalArgumentException("Member already has 3 or more books loaned!");
            }
            if (newBook.getCopiesAvailable() < 1) {
                throw new IllegalArgumentException("There are no copies of that book left!");
            }
            newMember.setNumberOfBooksLoaned(newMember.getNumberOfBooksLoaned() + 1);
            newBook.setCopiesAvailable(newBook.getCopiesAvailable() - 1);
        }

        if (loanModel.getLoanOption().equals(LoanOption.LOAN)){
            oldMember.setNumberOfBooksLoaned(oldMember.getNumberOfBooksLoaned() - 1);
            oldBook.setCopiesAvailable(oldBook.getCopiesAvailable() + 1);
        }
        else {
            oldMember.setNumberOfBooksLoaned(oldMember.getNumberOfBooksLoaned() + 1);
            oldBook.setCopiesAvailable(oldBook.getCopiesAvailable() - 1);
        }
        oldMember.removeLoan(loanModel);
        oldBook.removeLoan(loanModel);

        loanModel.setLoanOption(updateLoanRequest.getLoanOption());
        loanModel.setBook(newBook);
        loanModel.setMember(newMember);
        newMember.addLoan(loanModel);
        newBook.addLoan(loanModel);

        try {
            return loanRepository.save(loanModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public List<LoanModel> getMemberLoans(long memberId){
        MemberModel member = memberService.readOne(memberId);
        try {
            return loanRepository.findAllByMemberOrderByTimestampDesc(member);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

}