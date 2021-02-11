package com.kh.toy.board.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kh.toy.board.model.dao.BoardDao;
import com.kh.toy.board.model.vo.Board;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.common.jdbc.JDBCTemplate;
import com.kh.toy.common.util.file.FileUtil;	
import com.kh.toy.common.util.file.FileVo;

public class BoardService {

	JDBCTemplate jbt = JDBCTemplate.getInstance();

	BoardDao boardDao = new BoardDao();

	public void insertBoard(String userId, HttpServletRequest request) {

		Connection conn = jbt.getConnection();

		// 게시글 저장
		Map<String, List> boardData = new FileUtil().fileUpload(request);

		Board board = new Board();

		board.setUserId(userId);
		board.setTitle(boardData.get("title").get(0).toString());
		board.setContent(boardData.get("content").get(0).toString());

		try {
			boardDao.insertBoard(conn, board);

			for (FileVo fileData : (List<FileVo>) boardData.get("fileData")) {
				boardDao.insertFile(conn, fileData);
			}
			jbt.commit(conn);
		} catch (DataAccessException e) {
			jbt.rollback(conn);
			throw new ToAlertException(e.error, e);
		} finally {
			jbt.close(conn);
		}

	}

	public Map<String, Object> selectBoardDetail(String bdIdx) {
		Map<String, Object> commandMap = new HashMap<String, Object>();
		Connection conn = jbt.getConnection();

		try {

			// 게시글 정보 가져오기
			Board board = boardDao.selectBoardDetail(conn, bdIdx);
			List<FileVo> fileList = boardDao.selectFileWithBoard(conn, bdIdx);
			commandMap.put("board", board);
			commandMap.put("fileList", fileList);
		} finally {
			jbt.close(conn);
		}
		return commandMap;
	}

}
