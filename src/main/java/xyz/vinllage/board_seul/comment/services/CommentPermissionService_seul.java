package xyz.vinllage.board_seul.comment.services;

import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.comment.entities.Comment;
import xyz.vinllage.board_seul.post.entities.BoardData;
import xyz.vinllage.board_seul.post.services.BoardDataInfoService_seul;
import xyz.vinllage.board_seul.services.PermissionService;
import xyz.vinllage.member.libs.MemberUtil;

@Service
public class CommentPermissionService_seul extends PermissionService<Comment> {

    private final BoardDataInfoService_seul boardDataInfoService;

    public CommentPermissionService_seul(MemberUtil memberUtil, BoardDataInfoService_seul boardDataInfoService) {
        super(memberUtil);
        this.boardDataInfoService = boardDataInfoService;
    }

    @Override
    public boolean canAccess(Comment comment){
        BoardData boardData = boardDataInfoService.get(comment.getBoardDataSeq());
        return authCheck(boardData.getCommentAuthority());
    }
}