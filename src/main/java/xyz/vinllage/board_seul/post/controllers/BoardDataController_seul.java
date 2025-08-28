package xyz.vinllage.board_seul.post.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.board_seul.board.entities.Board_seul;
import xyz.vinllage.board_seul.board.services.BoardInfoService_seul;
import xyz.vinllage.board_seul.controllers.BoardSearch_seul;
import xyz.vinllage.board_seul.post.entities.BoardData_seul;
import xyz.vinllage.board_seul.post.services.BoardDataDeleteService_seul;
import xyz.vinllage.board_seul.post.services.BoardDataInfoService_seul;
import xyz.vinllage.board_seul.post.services.BoardDataUpdateService_seul;
import xyz.vinllage.board_seul.post.services.BoardPermissionService_seul;
import xyz.vinllage.global.search.ListData;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardDataController_seul {

    private final MemberUtil memberUtil;
    private final BoardDataInfoService_seul infoService;
    private final BoardPermissionService_seul permissionService;
    private final BoardInfoService_seul configInfoService;
    private final BoardDataUpdateService_seul updateService;
    private final BoardDataDeleteService_seul deleteService;

    // 게시글 목록 조회
    @GetMapping("/list/{bid}")
    public ListData<BoardData_seul> getList(@PathVariable("bid") String bid, @ModelAttribute BoardSearch_seul search) {
        try {
            search.setBid(bid);
            Board_seul board = configInfoService.get(bid);
            if (board == null) {
                return null;
            }

            ListData<BoardData_seul> data = infoService.getList(search);

            return data;
        } catch (Exception e) {
            return null;
        }
    }

    // 게시글 작성 폼 데이터 조회
    @GetMapping("/write/{bid}")
    public RequestBoardData_seul getWriteForm(@PathVariable("bid") String bid) {
        try {
            Board_seul board = configInfoService.get(bid);
            if (board == null) {
                return null;
            }

            if (!permissionService.canAccess(board)) {
                return null;
            }

            RequestBoardData_seul form = new RequestBoardData_seul();
            form.setBid(bid);
            form.setGid(UUID.randomUUID().toString());

            if (memberUtil.isLogin()) {
                form.setPoster(memberUtil.getMember().getName());
            } else {
                form.setGuest(true);
            }
            return form;
        } catch (Exception e) {
            return null;
        }
    }

    // 게시글 저장
    @RequestMapping(path="/save", method={RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<?> save(@Valid @RequestBody RequestBoardData_seul form, Errors errors) {
        try {
            String mode = StringUtils.hasText(form.getMode()) ? form.getMode() : "register";
            form.setMode(mode);

            if (errors.hasErrors()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "입력값 검증에 실패했습니다.");

                Map<String, String> fieldErrors = new HashMap<>();
                errors.getFieldErrors().forEach(error ->
                        fieldErrors.put(error.getField(), error.getDefaultMessage())
                );
                errorResponse.put("errors", fieldErrors);

                return ResponseEntity.badRequest().body(errorResponse);
            }

            updateService.process(form);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", mode.equals("update") ? "글이 수정되었습니다." : "글이 등록되었습니다.",
                    "data", form
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "글 저장에 실패했습니다."
            ));
        }
    }

    // 게시글 상세 조회
    @GetMapping("/view/{seq}")
    public BoardData_seul view(@PathVariable("seq") Long seq) {
        try {
            BoardData_seul boardData = infoService.get(seq);
            if (boardData == null) {
                return null;
            }

            if (!permissionService.canView(boardData)) {
                return null;
            }
            BoardData_seul data = new BoardData_seul();
            data.setCanDelete(permissionService.canEdit(boardData));
            data.setGuest(permissionService.memberOrGuest(boardData));

            return data;

        } catch (Exception e) {
            return null;
        }
    }

    // 게시글 수정 폼 데이터 조회
    @GetMapping("/update/{seq}")
    public BoardData_seul getUpdateForm(@PathVariable("seq") Long seq, HttpSession session) {
        try {
            BoardData_seul boardData = infoService.get(seq);
            if (boardData == null) {
                return null;
            }

            if (!permissionService.canEdit(boardData)) {
                System.out.println(boardData);
                return null;
            }

            // 비회원 글인 경우 세션 검증
            if (boardData.getMember() == null) {
                String sessionKey = "guest_verified_" + seq + "_update";
                Long expireTime = (Long) session.getAttribute(sessionKey);
                if (expireTime == null || System.currentTimeMillis() > expireTime) {
                    return null;
                }
            }

            return boardData;
        } catch (Exception e) {
            return null;
        }
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{seq}")
    public ResponseEntity<?> delete(@PathVariable("seq") Long seq, HttpSession session) {
        try {
            BoardData_seul boardData = infoService.get(seq);
            if (boardData == null) {
                return ResponseEntity.notFound().build();
            }

            if (!permissionService.canEdit(boardData)) {
                return ResponseEntity.status(403).body(Map.of(
                        "success", false,
                        "message", "삭제 권한이 없습니다."
                ));
            }

            // 비회원 글이고 관리자가 아닌 경우 세션 검증
            if (boardData.getMember() == null && !memberUtil.isAdmin()) {
                String sessionKey = "guest_verified_" + seq + "_delete";
                Long expireTime = (Long) session.getAttribute(sessionKey);
                if (expireTime == null || System.currentTimeMillis() > expireTime) {
                    return ResponseEntity.status(401).body(Map.of(
                            "success", false,
                            "message", "비밀번호 확인이 필요합니다.",
                            "needPassword", true
                    ));
                }

                // 사용 후 세션 제거
                session.removeAttribute(sessionKey);
            }

            deleteService.delete(boardData.getSeq());

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "글이 삭제되었습니다.",
                    "redirectTo", "/board/list/" + boardData.getBoard().getBid()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "글 삭제에 실패했습니다."
            ));
        }
    }

    // 비회원 비밀번호 확인
    @PostMapping("/check-guest-password")
    public ResponseEntity<?> checkGuestPassword(
            @RequestBody Map<String, Object> request,
            HttpSession session) {
        try {
            Long seq = Long.valueOf(request.get("seq").toString());
            String password = request.get("password").toString();
            String action = request.get("action").toString();

            BoardData_seul boardData = infoService.get(seq);
            if (boardData == null) {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "오류가 발생했습니다."
            ));
        }
        return null;
    }

}
