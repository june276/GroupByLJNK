package com.project.domain;

import com.project.domain.Board;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

        boolean result = Board.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Board board = (Board)target;

        String title =  board.getTitle();
        if(title==null|| title.trim().isEmpty()){
            errors.rejectValue("title","제목을 적어주세요");
        }
        String content = board.getContent();
        if(content==null || content.trim().isEmpty()){
            errors.rejectValue("content","내용을 입력해주세요");
        }
    }
}
