package xyz.vinllage.board.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import xyz.vinllage.global.exceptions.NotFoundException;
import xyz.vinllage.board.repositories.BaseRepository;

@RequiredArgsConstructor
public abstract class InfoService<T, ID> {
    protected final HttpServletRequest request;
    protected abstract BaseRepository<T, ID> getRepository();


    /**
     * 단일 조회
     */
    public T get(ID id) {
        T item = getRepository().findById(id)
                .orElseThrow(NotFoundException::new);
        return item;
    }

}
