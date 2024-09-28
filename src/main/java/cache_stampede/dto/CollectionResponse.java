package cache_stampede.dto;

public record CollectionResponse<T>(
        T data
) {

    public static <T> CollectionResponse<T> from(final T data) {
        return new CollectionResponse<>(data);
    }
}
