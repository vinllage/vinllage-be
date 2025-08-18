package xyz.vinllage.board_seul.post.services;

import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.post.entities.BoardData;
import xyz.vinllage.board_seul.services.PermissionService;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.Objects;

@Service
public class BoardPermissionService extends PermissionService<BoardData> {


    public BoardPermissionService(MemberUtil memberUtil) {
        super(memberUtil);
    }

    @Override
    protected boolean canAccess(BoardData boardData){
        Authority auth=boardData.getBoard().getWriteAuthority();
        return authCheck(auth);
    }

    /**
     * 조회 권한 확인 (비밀글인 경우)
     */
    public boolean canView(BoardData boardData) {
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
