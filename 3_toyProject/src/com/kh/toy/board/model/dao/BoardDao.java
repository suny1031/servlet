package com.kh.toy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.kh.toy.board.model.vo.Board;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.jdbc.JDBCTemplate;
import com.kh.toy.common.util.file.FileVo;

public class BoardDao {

	JDBCTemplate jdb = JDBCTemplate.getInstance();

	// 게시판 테이블에 게시글 저장
	public int insertBoard(Connection conn, Board board) {
		int res = 0;
		String sql = "insert into tb_board (bd_idx,user_id,title,content) Values('b' || sc_board_idx.nextval, ?, ?, ?)";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getUserId());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);

		} finally {
			jdb.close(pstm);
		}

		return res;

	}

	// 파일테이블에 파일정보 저장
	public int insertFile(Connection conn, FileVo fileData) {
		int res = 0;
		String bdIdx = "";

		if (fileData.getTypeIdx() == null) {
			// 1. 새로 등록되는 게시글의 파일 정보 저장
			// typeIdx값이 시퀀스의 currval
			bdIdx = " 'b' || sc_board_idx.currval";

		} else {

			// 2. 수정할 때 사용자가 파일을 추가 등록해서 파일 정보 저장
			// 수정할 게시글의 bdIdx값
			bdIdx = "'" + fileData.getTypeIdx() + "'";

		}

		String sql = "insert into tb_file (f_idx,type_idx,origin_file_name,rename_file_name, save_path) values(sc_file_idx.nextVal,"
				+ bdIdx + ",?,?,?)";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, fileData.getOriginFileName());
			pstm.setString(2, fileData.getRenameFileName());
			pstm.setString(3, fileData.getSavePath());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IF01, e);
		} finally {
			jdb.close(pstm);
		}

		return res;
	}

	// 게시판글 상세
	public Board selectBoardDetail(Connection conn, String bdIdx) {
		Board board = null;

		PreparedStatement pstm = null;

		ResultSet rset = null;

		String sql = "select bd_idx, user_id, reg_date, title, content from tb_board where bd_idx = ?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();
			if (rset.next()) {
				board = new Board();
				board.setBdIdx(rset.getString(1));
				board.setUserId(rset.getString(2));
				board.setRegDate(rset.getDate(3));
				board.setTitle(rset.getString(4));
				board.setContent(rset.getString(5));

			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		} finally {
			jdb.close(rset, pstm);
		}

		return board;

	}

	public List<FileVo> selectFileWithBoard(Connection conn, String bdIdx) {

		List<FileVo> res = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "select f_idx,type_idx,origin_file_name,rename_file_name,save_path,reg_date,is_del from tb_file where type_idx = ? ";

		res = new ArrayList<FileVo>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rset = pstm.executeQuery();

			while (rset.next()) {
				FileVo fileVo = new FileVo();
				fileVo.setFidx(rset.getInt(1));
				fileVo.setTypeIdx(rset.getString(2));
				fileVo.setOriginFileName(rset.getString(3));
				fileVo.setRenameFileName(rset.getString(4));
				fileVo.setSavePath(rset.getString(5));
				fileVo.setRegDate(rset.getDate(6));
				fileVo.setIsDel(rset.getInt(7));
				res.add(fileVo);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SF01, e);

		}

		return res;
	}

}
