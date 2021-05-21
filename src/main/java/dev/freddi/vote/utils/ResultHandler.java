package dev.freddi.vote.utils;

@FunctionalInterface
public interface ResultHandler<T, R> {
    public void apply(T t);
}
