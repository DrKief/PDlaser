#### 3. `API-Reference.md`
# API Reference

All backend endpoints are relative to the application's base URL (e.g., `http://localhost:8080`).

**Note on Error Handling:** The API uses RFC 7807 Problem Details for standardizing error responses. All errors return a `traceId` for debugging purposes.

## 1. Core Image Operations

### List All Images
Retrieves a list of all available images stored in the system (metadata only).
* **URL:** `/images`
* **Method:** `GET`
* **Response:** `200 OK`
  ```json
  [
    { "id": 1, "name": "vacation.jpg", "keywords": ["summer"] },
    { "id": 2, "name": "profile.png", "keywords": [] }
  ]
  ```

### Get Image Content
Retrieves the actual binary content of a specific image.
* **URL:** `/images/{id}`
* **Method:** `GET`
* **Response:** 
  * `200 OK` (Returns binary image data with appropriate `image/*` Content-Type).
  * `404 Not Found`

### Upload Image (Asynchronous)
Uploads a new image file to the server. The backend automatically calculates SHA-256 hashes (to prevent duplicates). Image feature vectors (HOG, HSV, RGB, CIELAB) are extracted **asynchronously** in the background.
* **URL:** `/images`
* **Method:** `POST`
* **Content-Type:** `multipart/form-data`
* **Body Form-Data:** `file` (The image file to upload), `keywords` (Optional, list of tags)
* **Response:** 
  * `202 Accepted`
    ```json
    {
      "message": "Image accepted for background processing.",
      "id": 1,
      "status_url": "/images/1/status"
    }
    ```
  * `415 Unsupported Media Type`
  * `400 Bad Request` (If file is empty)
  * `413 Content Too Large`

### Check Image Processing Status
Retrieves the background processing status for vector extraction via Long-Polling.
* **URL:** `/images/{id}/status`
* **Method:** `GET`
* **Response:**
  * `200 OK`
    ```json
    {
      "id": 1,
      "extraction_status": "COMPLETED" // Values: PENDING, PROCESSING, COMPLETED, FAILED
    }
    ```
  * `202 Accepted` (If request times out before process completes, used to keep the connection alive gracefully).
  * `404 Not Found`

### Delete Image
Deletes a specific image and its associated metadata/vectors from the system. Architecture ensures the physical file is deleted before the DB record.
* **URL:** `/images/{id}`
* **Method:** `DELETE`
* **Response:** 
  * `204 No Content`
  * `404 Not Found`

---

## 2. Metadata & Keywords

### Get Image Metadata
Retrieves file metadata, processing status, and associated keywords.
* **URL:** `/images/{id}/metadata`
* **Method:** `GET`
* **Response:** `200 OK`
  ```json
  {
    "Name": "vacation.jpg",
    "Type": "image/jpeg",
    "Size": "800*600",
    "Keywords": ["nature", "summer"],
    "Extraction_Status": "COMPLETED"
  }
  ```

### Get All Keywords
Retrieves a distinct, alphabetical list of all tags currently used in the database.
* **URL:** `/images/keywords`
* **Method:** `GET`
* **Response:** `200 OK` (Array of strings)

### Add Keyword
Tags an image with a specific keyword. Tags are normalized automatically (lowercase, spaces replaced by underscores).
* **URL:** `/images/{id}/keywords`
* **Method:** `PUT`
* **Query Params:** `tag` (string)
* **Response:** `204 No Content`, `404 Not Found`

### Delete Keyword
Removes a keyword from an image.
* **URL:** `/images/{id}/keywords`
* **Method:** `DELETE`
* **Query Params:** `tag` (string)
* **Response:** `204 No Content`, `404 Not Found`, `400 Bad Request` (If tag doesn't exist on image)

---

## 3. Search & Similarity

### Search by Attributes
Finds images matching specific metadata criteria.
* **URL:** `/images/search`
* **Method:** `GET`
* **Query Params (Optional):**
  * `name` (string): Substring of the filename.
  * `format` (string): File extension (e.g., `jpeg`).
  * `size` (string): Formatted as `width,height` (e.g., `800,600`).
  * `keywords` (list of strings): comma-separated tags.
* **Response:** `200 OK` (Array of IDs), `404 Not Found`

### Find Similar Images
Uses vector-based math (`pgvector` HNSW index operators) to find visually similar images. Requires `extraction_status` to be `COMPLETED`.
* **URL:** `/images/{id}/similar`
* **Method:** `GET`
* **Query Params:**
  * `number` (int, default: 10): Limit of results returned.
  * `descriptor` (string, default: `gradient`): Valid values are `gradient` (HOG), `saturation` (HSV), `rgb` (RGB), and `cielab` (CIELAB).
* **Response:** `200 OK`
  ```json
  [
    { "id": 5, "filename": "beach.jpg", "score": 0.9842 },
    { "id": 12, "filename": "ocean.png", "score": 0.8510 }
  ]
  ```