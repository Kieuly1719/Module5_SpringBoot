package com.codegym.appborrowingbook.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LibraryAspect {

    private static int visitCount = 0;

    @Before(
            "execution(* com.codegym.appborrowingbook.controller.*.*(..))"
    )
    public void countVisitors(JoinPoint joinPoint) {

        visitCount++;

        System.out.println(
                "[VISIT] " +
                        joinPoint.getSignature().getName() +
                        " - Total visit: " +
                        visitCount
        );
    }
    @AfterReturning(
            pointcut =
                    "execution(* com.codegym.appborrowingbook.service.BookService.borrowBook(..))",
            returning = "code"
    )
    public void logBorrowSuccess(Object code) {

        System.out.println(
                "[BORROW SUCCESS] Borrow code = "
                        + code
        );
    }
    @AfterReturning(
            "execution(* com.codegym.appborrowingbook.service.BookService.returnBook(..))"
    )
    public void logReturnSuccess() {

        System.out.println(
                "[RETURN SUCCESS] Book returned"
        );
    }
    @AfterThrowing(
            pointcut =
                    "execution(* com.codegym.appborrowingbook.service.BookService.borrowBook(..))",
            throwing = "ex"
    )
    public void logBorrowError(Exception ex) {

        System.out.println(
                "[BORROW ERROR] "
                        + ex.getMessage()
        );
    }
    @AfterThrowing(
            pointcut =
                    "execution(* com.codegym.appborrowingbook.service.BookService.returnBook(..))",
            throwing = "ex"
    )
    public void logReturnError(Exception ex) {

        System.out.println(
                "[RETURN ERROR] "
                        + ex.getMessage()
        );
    }
}