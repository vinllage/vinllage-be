package xyz.vinllage.board.services;

import jakarta.servlet.http.HttpServletRequest;
import xyz.vinllage.board.repositories.BaseRepository;

public abstract class UpdateService<T, ID> {
    protected abstract BaseRepository<T, ID> getRepository();

    public abstract T beforeProcess(T item);
    public abstract void afterProcess(T item);

    public void process(T item){
        item= beforeProcess(item);
        getRepository().saveAndFlush(item);
        afterProcess(item);
    }
}
