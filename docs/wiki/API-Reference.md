# API Reference

All backend endpoints are relative to the application's base URL (e.g., `http://localhost:8080`).

**Note on Error Handling:** The API uses RFC 7807 Problem Details for standardizing error responses. All errors return a `traceId` for debugging purposes.

## 1. Authentication & Users

### Register User
Creates a new account with `ROLE_USER` privileges.
* **URL:** `/auth/register`
* **Method:** `POST`
* **Body:** `{ "username": "alice", "password": "secure" }`
* **Response:** `200 OK` (Success), `400 Bad Request` (Username taken)

### Login
Authenticates a user and issues a stateless JSON Web Token (JWT).
* **URL:** `/auth/login`
* **Method:** `POST`
* **Body:** `{ "username": "alice", "password": "secure" }`
* **Response:** `200 OK` with `{ "token": "ey..." }`

---

## 2. Core Image Operations

### List All Images (Paginated)
Retrieves a paginated list of all images. Supports filtering by the authenticated user's ownership.
* **URL:** `/images`
* **Method:** `GET`
* **Query Params:** `page` (default: 0), `size` (default: 30), `mine` (boolean, default: false)
* **Response:** `200 OK`
  ```json
  {
    "content": [
      { "id": 1, "uploader": "alice", "keywords": ["summer"], "extraction_status": "COMPLETED" }
    ],
    "totalElements": 1,
    "hasNext": false
  }
  ```

### Get Image Content
Retrieves the actual binary content of a specific image.
* **URL:** `/images/{id}`
* **Method:** `GET`
* **Response:** `200 OK` (Binary image data), `404 Not Found`

### Upload Image (Asynchronous)
Uploads a new image file. Calculates SHA-256 hashes to prevent duplicates. Vectors (HOG, HSV, RGB, CIELAB, Semantic) are extracted asynchronously via virtual threads.
* **URL:** `/images`
* **Method:** `POST`
* **Content-Type:** `multipart/form-data`
* **Headers:** `Authorization: Bearer <JWT>`
* **Body:** `file` (Image binary), `keywords` (Optional, list of tags)
* **Response:** `202 Accepted` with `{ "id": 1 }`, `415 Unsupported Media Type`, `409 Conflict` (Duplicate)

### Check Image Processing Status (Long-Polling)
Retrieves the background processing status. Uses `DeferredResult` to hang the connection securely until the status changes or a timeout occurs.
* **URL:** `/images/{id}/status`
* **Method:** `GET`
* **Response:** `200 OK` or `202 Accepted` (Timeout)
  ```json
  {
    "id": 1,
    "extraction_status": "COMPLETED" // PENDING, PROCESSING, COMPLETED, FAILED
  }
  ```

### Delete Image
Permanently deletes an image. Protected by JWT claims (must be original uploader or Admin).
* **URL:** `/images/{id}`
* **Method:** `DELETE`
* **Headers:** `Authorization: Bearer <JWT>`
* **Response:** `204 No Content`, `403 Forbidden`, `404 Not Found`

---

## 3. Metadata & Keywords

### Get Image Metadata
Retrieves file metadata, dimensions, processing status, and associated keywords.
* **URL:** `/images/{id}/metadata`
* **Method:** `GET`
* **Response:** `200 OK`
  ```json
  {
    "Name": "vacation.jpg",
    "Type": "image/jpeg",
    "Size": "800*600",
    "Keywords": ["nature", "ai:outdoor"],
    "Extraction_Status": "COMPLETED"
  }
  ```

### Get/Search Keywords
* **URL:** `/images/keywords` (GET all unique keywords available to user)
* **URL:** `/images/keywords/search?q=xyz` (Search for autocomplete)
* **URL:** `/images/keywords/popular?limit=15` (Top tags)
* **Method:** `GET`
* **Response:** `200 OK` (Array of strings)

### Add / Delete Keyword
* **URL:** `/images/{id}/keywords?tag=xyz`
* **Method:** `PUT` (Add) / `DELETE` (Remove)
* **Response:** `204 No Content`, `404 Not Found`

---

## 4. Search & Similarity

### Search by Attributes (Paginated)
Finds images matching specific keyword tags.
* **URL:** `/images/search`
* **Method:** `GET`
* **Query Params:** `keywords` (List), `page`, `size`, `mine`
* **Response:** `200 OK` (Paginated Object)

### Find Similar Images (Database Source)
Uses `pgvector` HNSW indexes to find visually similar images.
* **URL:** `/images/{id}/similar`
* **Method:** `GET`
* **Query Params:** `number` (Limit), `descriptor` (`semantic`, `cielab`, `weighted`, `gradient`, `saturation`, `rgb`)
* **Response:** `200 OK`
  ```json
  [ { "id": 5, "filename": "beach.jpg", "score": 0.9842 } ]
  ```

### Ephemeral Similarity Search (Upload Source)
Upload an image purely to search the database. Vectors are extracted in RAM and searched without persisting the image to disk or the DB.
* **URL:** `/images/search/ephemeral`
* **Method:** `POST`
* **Content-Type:** `multipart/form-data`
* **Body:** `file` (Binary), `number` (Limit), `descriptor` (Algorithm)
* **Response:** `200 OK` (Array of similar images)

---

## 5. Administrative Controls

*Requires `ROLE_ADMIN` JWT claim.*

* **Start Bulk Import:** `POST /admin/unsplash/import` (Body: `{ "limit": 50, "offset": 0 }`)
* **Start Keyword Import:** `POST /admin/unsplash/import/keyword` (Body: `{ "keyword": "mountain", "limit": 25 }`)
* **Check Admin Status:** `GET /admin/unsplash/status`