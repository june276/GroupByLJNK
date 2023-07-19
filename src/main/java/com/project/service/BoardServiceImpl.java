package com.project.service;

import com.project.domain.Board;
import com.project.repository.BoardRepository;
import com.project.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {


    private BoardRepository boardRepository;
    private UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){

        boardRepository=sqlSession.getMapper(BoardRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
    }


    @Override
    public int write(Board board) {

        return boardRepository.write(board);
    }



    @Override
    public Board detail(Long id) {
        boardRepository.viewCnt(id);
        Board board = boardRepository.findById(id);

        return board;
    }

    @Override
    public List<Board> list() {

        return boardRepository.countAll();
    }

    @Override
    public List<Board> list(Integer page, Model model) {
        return null;
    }

    @Override
    public Board selectById(Long id) {
        Board board = boardRepository.findById(id);
        return board;
    }

    @Override
    public int update(Board board) {

        return boardRepository.update(board);
    }

    @Override
    public int deleteById(Long id) {
        int result = 0;

        Board board = boardRepository.findById(id);
        if(board != null){
            result = boardRepository.delete(board);
        }

        return result;
    }
}