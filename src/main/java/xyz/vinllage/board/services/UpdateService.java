package xyz.vinllage.board.services;

import jakarta.servlet.http.HttpServletRequest;
import xyz.vinllage.board.repositories.BaseRepository;

//데이터 타입, 아이디, request
public abstract class UpdateService<T, ID, R> {
    protected abstract BaseRepository<T, ID> getRepository();

    public abstract T beforeProcess(R item);
    public abstract void afterProcess(T item);

    public void process(R request){
        T item= beforeProcess(request);
        getRepository().saveAndFlush(item);
        afterProcess(item);
    }
}
