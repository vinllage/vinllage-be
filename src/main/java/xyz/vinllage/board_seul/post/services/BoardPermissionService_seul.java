package xyz.vinllage.board_seul.post.services;

import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.post.entities.BoardData_Seul;
import xyz.vinllage.board_seul.services.PermissionService;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.libs.MemberUtil;

@Service
public class BoardPermissionService_seul extends PermissionService<BoardData_Seul> {


    public BoardPermissionService_seul(MemberUtil memberUtil) {
        super(memberUtil);
    }

    @Override
    protected boolean canAccess(BoardData_Seul boardData){
        Authority auth=boardData.getBoardSeul().getWriteAuthority();
        return authCheck(auth);
    }

    /**
     * 조회 권한 확인 (비밀글인 경우)
     */
    public boolean canView(BoardData_Seul boardData) {
        if (canAccess(boardData)) {
            // 비밀글이 아니면 누구나 조회 가능
            if (!boardData.isSecret()) {
                return true;
            }
            //비밀글인 경우 작성자만 조회 가능
            return memberOrGuest(boardData);
        } else {return false;}

    }

}
