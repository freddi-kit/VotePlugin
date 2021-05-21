package dev.freddi.vote.utils;

public class Result<Success> {
    private Success success;
    private Failure failure;

    public Result(Success success) {
        this.success = success;
    }

    public Result(Failure failure) {
        this.failure = failure;
    }

    public void handle(ResultHandler<Success, Void> onSuccess, ResultHandler<Failure, Void> onFailure) {
        if(success != null) {
            onSuccess.apply(success);
        } else {
            onFailure.apply(failure);
        }
    }
}
