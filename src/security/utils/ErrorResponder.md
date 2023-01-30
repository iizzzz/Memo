```java
/**
 * ErrorResponse를 출력 스트림으로 생성
 */

public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus httpStatus) throws IOException {
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(httpStatus);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
```