

# HTTP Status Codes


| Operation             | Status Code                 | When to Use                                            |
| --------------------- | --------------------------- | ------------------------------------------------------ |
| GET Success           | `200 OK`                    | Resource found                                         |
| GET Not Found         | `404 Not Found`             | Resource doesn't exist                                 |
| POST Success          | `201 Created`               | Resource created successfully                          |
| POST Accepted         | `202 Accepted`              | Processing asynchronously                              |
| PUT Success           | `200 OK`                    | Updated and returning updated resource                 |
| PUT No Content        | `204 No Content`            | Updated, no response body                              |
| PATCH Success         | `200 OK`                    | Partial update with response                           |
| PATCH No Content      | `204 No Content`            | Partial update, no body                                |
| DELETE Success        | `204 No Content`            | Deleted successfully                                   |
| DELETE Success        | `200 OK`                    | Deleted and returning confirmation/body                |
| Invalid Input         | `400 Bad Request`           | Validation or malformed request                        |
| Authentication Failed | `401 Unauthorized`          | User not authenticated                                 |
| Permission Denied     | `403 Forbidden`             | Authenticated but lacks permission                     |
| Duplicate Resource    | `409 Conflict`              | Resource already exists                                |
| Validation Error      | `422 Unprocessable Entity`  | Request syntax is valid but business validation failed |
| Too Many Requests     | `429 Too Many Requests`     | Rate limit exceeded                                    |
| Server Error          | `500 Internal Server Error` | Unexpected server error                                |
| Service Down          | `503 Service Unavailable`   | Temporary outage or maintenance                        |

