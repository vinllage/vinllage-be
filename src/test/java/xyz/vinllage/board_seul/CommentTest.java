package xyz.vinllage.board_seul;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.vinllage.board_seul.comment.entities.Comment;
import xyz.vinllage.board_seul.comment.repositories.CommentRepository;
import xyz.vinllage.board_seul.comment.services.CommentDeleteService_seul;
import xyz.vinllage.board_seul.comment.services.CommentInfoService_seul;
import xyz.vinllage.global.exceptions.NotFoundException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentDeleteServiceTest {

    @Mock
    private CommentInfoService_seul commentInfoService;

    @Mock
    private CommentRepository repository;

    @InjectMocks
    private CommentDeleteService_seul deleteService;

    @Test
    void deletetete() {
        // given
        Long commentId = 1L;
        Comment comment = new Comment();
        comment.setSeq(commentId);
        comment.setContent("테스트 댓글");

        when(commentInfoService.get(commentId)).thenReturn(comment);

        // when
        deleteService.delete(commentId);

        // then
        assertNotNull(comment.getDeletedAt());
        assertTrue(comment.getDeletedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
        verify(repository).save(comment);
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
