package xyz.vinllage.board_seul;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.vinllage.board_seul.comment.entities.Comment_seul;
import xyz.vinllage.board_seul.comment.repositories.CommentRepository_seul;
import xyz.vinllage.board_seul.comment.services.CommentDeleteService_seul;
import xyz.vinllage.board_seul.comment.services.CommentInfoService_seul;
import xyz.vinllage.global.exceptions.NotFoundException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentSeulDeleteServiceTest {

    @Mock
    private CommentInfoService_seul commentInfoService;

    @Mock
    private CommentRepository_seul repository;

    @InjectMocks
    private CommentDeleteService_seul deleteService;

    @Test
    void deletetete() {
        // given
        Long commentId = 1L;
        Comment_seul commentSeul = new Comment_seul();
        commentSeul.setSeq(commentId);
        commentSeul.setContent("테스트 댓글");

        when(commentInfoService.get(commentId)).thenReturn(commentSeul);

        // when
        deleteService.delete(commentId);

        // then
        assertNotNull(commentSeul.getDeletedAt());
        assertTrue(commentSeul.getDeletedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
        verify(repository).save(commentSeul);
    }

    @Test
    void testst() {
        // given
        Long invalidId = 999L;
        when(commentInfoService.get(invalidId)).thenThrow(new NotFoundException());

        // when & then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> deleteService.delete(invalidId));

        assertEquals("댓글을 찾을 수 없습니다.", exception.getMessage());
                verify(repository, never()).save(any());
    }
}
