package cache_stampede.global.dto;

public record CollectionResponse<T>(
        T data
) {

    public static <T> CollectionResponse<T> from(final T data) {
        return new CollectionResponse<>(data);
    }
}
