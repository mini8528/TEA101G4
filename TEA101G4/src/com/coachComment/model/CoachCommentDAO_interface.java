package com.coachComment.model;

import java.util.List;


public interface CoachCommentDAO_interface {
	
	public void insert(CoachCommentVO coachCommentVO);
    public void update(CoachCommentVO coachCommentVO);
    public void update_status(CoachCommentVO coachCommentVO);
    
    public void delete(String coachCommentID);
    
    public CoachCommentVO findByPrimaryKey(String coachCommentID);
    public CoachCommentVO findByMemberID(String coachCommentID);
    public List<CoachCommentVO> getAll();

}
