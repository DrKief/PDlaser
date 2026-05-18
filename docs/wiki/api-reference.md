# API Reference

All backend endpoints are relative to the application's base URL (e.g., `http://localhost:8080`).

**Note on Error Handling:** The API returns RFC 7807 Problem Details for errors.

## 1. Authentication & Users

### Register User

Creates a new account with `ROLE_USER` privileges. Will auto-approve if the Admin setting is toggled.

- **URL:** `/auth/register`
- **Method:** `POST`
- **Body:** `{ "username": "alice", "password": "secure" }`
- **Response:** `200 OK` (Success), `400 Bad Request` (Username taken)

### Login

Authenticates a user and returns a JSON Web Token (JWT) valid for 24 hours.

- **URL:** `/auth/login`
- **Method:** `POST`
- **Body:** `{ "username": "alice", "password": "secure" }`
- **Response:** `200 OK` with `{ "token": "ey..." }`, `403 Forbidden` (Pending approval)

---

## 2. Core Image Operations & S3 Storage

### List All Images (Paginated)

Retrieves a paginated list of all images. Filters based on user ownership.

- **URL:** `/images`
- **Method:** `GET`
- **Query Params:** `page` (default: 0), `size` (default: 30), `mine` (boolean, default: false)
- **Response:** `200 OK` (Contains metadata, does not return binary payload)

### Get Image Content

Retrieves the binary payload of an image from Garage S3.

- **URL:** `/images/{id}`
- **Method:** `GET`
- **Response:** `200 OK` (Binary image data), `404 Not Found`

### Upload Image (Asynchronous)

Uploads a new image file. Saves the binary to Garage S3 and computes a SHA-256 hash. Feature vectors are extracted asynchronously.

- **URL:** `/images`
- **Method:** `POST`
- **Content-Type:** `multipart/form-data`
- **Headers:** `Authorization: Bearer <JWT>`
- **Body:** `file` (Image binary), `keywords` (Optional array)
- **Response:** `202 Accepted` with `{ "id": 1 }`, `409 Conflict` (Duplicate Hash)

### Check Image Processing Status (HTTP Long-Polling)

Retrieves the background extraction status. Uses Spring's `DeferredResult` to hold the connection until a status change occurs or a 10-second timeout is reached.

- **URL:** `/images/{id}/status`
- **Method:** `GET`
- **Response:** `200 OK` (State Changed) or `202 Accepted` (Timeout - reconnect)

### Download Original (S3 / Stream)

Returns the image with a `Content-Disposition: attachment` header.

- **URL:** `/images/{id}/download`
- **Method:** `GET`
- **Response:** `200 OK` with `Content-Disposition: attachment`

### Delete Image

Deletes the image from PostgreSQL and Garage S3.

- **URL:** `/images/{id}`
- **Method:** `DELETE`
- **Response:** `204 No Content`, `403 Forbidden`

---

## 3. Metadata & Keywords

### Get Image Metadata

- **URL:** `/images/{id}/metadata`
- **Method:** `GET`
- **Response:** `200 OK` (JSON Map including Provider, Status, Camera Make, Location)

### Autocomplete Keyword Routes

Endpoints optimized for Vue 3.5 autocomplete rendering.

- **GET** `/images/keywords` (All available)
- **GET** `/images/keywords/search?q=xyz` (Search string mapping)
- **GET** `/images/keywords/popular?limit=15` (Top tags sorted by distribution)

### Add / Delete Keyword

- **PUT** `/images/{id}/keywords?tag=xyz`
- **DELETE** `/images/{id}/keywords?tag=xyz`

---

## 4. Similarity Search (pgvector HNSW)

### Database Target Search

Uses HNSW graph indexing to execute hyper-fast mathematical distance calculations based on the selected descriptor logic.

- **URL:** `/images/{id}/similar`
- **Method:** `GET`
- **Query Params:** `number` (Limit), `descriptor` (`semantic`, `cielab`, `weighted`, `gradient`, `saturation`, `rgb`)
- **Response:** `200 OK` Array of matched images.

### Ephemeral Similarity Search

Extracts SigLIP 2 vectors and BoofCV arrays entirely in RAM, executes the query against the database, and discards the image without persisting it to S3.

- **URL:** `/images/search/ephemeral`
- **Method:** `POST`
- **Content-Type:** `multipart/form-data`
- **Body:** `file`, `number`, `descriptor`
- **Response:** `200 OK` Array of matched images.

---

## 5. Administrative Controls (ROLE_ADMIN)

- **Upload Metadata File:** `POST /admin/unsplash/upload` (multipart)
- **Check Catalog:** `GET /admin/unsplash/catalog` (Paginated view of remote URL mappings)
- **Batch Import:** `POST /admin/unsplash/import/batch` (Triggers asynchronous S3 background downloading)
- **Check Worker Status:** `GET /admin/unsplash/status`
- **Approve User Account:** `PUT /admin/users/{id}/approve`
