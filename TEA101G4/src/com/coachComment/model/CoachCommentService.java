package com.coachComment.model;

import java.sql.Timestamp;
import java.util.List;

import com.member.model.MemberVO;

public class CoachCommentService {

	private CoachCommentDAO_interface dao;

	public CoachCommentService() {
		dao = new CoachCommentDAO();
	}

	public CoachCommentVO addCoachComment(String memberID, String memberID2, String commText, Integer commStar,
			Timestamp addDate, Timestamp editDate, String status) {

		CoachCommentVO coachCommentVO = new CoachCommentVO();
		
		coachCommentVO.setMemberID(memberID);
		coachCommentVO.setMemberID2(memberID2);
		coachCommentVO.setCommText(commText);
		coachCommentVO.setCommStar(commStar);
		coachCommentVO.setAddDate(addDate);
		coachCommentVO.setEditDate(editDate);
		coachCommentVO.setStatus(status);
		dao.insert(coachCommentVO);
		
		return coachCommentVO;
	}

	public CoachCommentVO updateCoachComment(String coachCommentID, String memberID, String memberID2, String commText, Integer commStar,
			Timestamp addDate, Timestamp editDate, String status) {
		
		System.out.println("=== service_update and start ===");
		CoachCommentVO coachCommentVO = new CoachCommentVO();
		
		coachCommentVO.setCoachCommentID(coachCommentID);
		coachCommentVO.setMemberID(memberID);
		coachCommentVO.setMemberID2(memberID2);
		coachCommentVO.setCommText(commText);
		coachCommentVO.setCommStar(commStar);
		coachCommentVO.setAddDate(addDate);
		coachCommentVO.setEditDate(editDate);
		coachCommentVO.setStatus(status);
		dao.update(coachCommentVO);
		
		System.out.println("=== service_update and set_data complete ===");
		return coachCommentVO;
	}
	
	public void deleteCoachComment(String coachCommentID) {
		dao.delete(coachCommentID);
	}

	public CoachCommentVO getOneCoachComment(String coachCommentID) {
		return dao.findByPrimaryKey(coachCommentID);
	}
	
	public CoachCommentVO getOnememberID(String memberID) {
		return dao.findByPrimaryKey(memberID);
	}

	public List<CoachCommentVO> getAll() {
		return dao.getAll();
	}
	
	public List<CoachCommentVO> getOneCoachCommentByMember(String memberID){
		return dao.getOneCoachCommentByMember(memberID);
	}
	
	public String getMemberIDFromCoachClassID(String coachClassID) {
		return dao.getMemberIDFromCoachClassID(coachClassID);
	}
	
	public List<MemberVO> getMemberCommentName (String memberID){
		return dao.getMemberCommentName(memberID);
		
	}

	public void update_status(String coachCommentID, String status, Timestamp editDate) {
		dao.update_status(coachCommentID, status, editDate);
		
	}
	
	
}
