import threading
import time
import sys

# Try to import requests, but fallback or warn if not present
try:
    import requests
except ImportError:
    print("This script requires the 'requests' library.")
    print("Please install it using: pip install requests")
    sys.exit(1)

URL = "http://devproject-backend-i2lkcs-de68ec-129-151-226-127.traefik.me/images"
THREAD_COUNT = 50  # Number of concurrent threads

def worker():
    """
    Worker thread function.
    Creates a session to reuse TCP connections for better performance.
    """
    session = requests.Session()
    while True:
        try:
            response = session.get(URL)
            print(f"[{threading.current_thread().name}] Status: {response.status_code}")
        except Exception as e:
            print(f"[{threading.current_thread().name}] Error: {e}")
            # Add a small sleep to prevent creating new connections too instantly on failure
            time.sleep(0.1)

def main():
    print(f"Starting {THREAD_COUNT} threads to hammer: {URL}")
    print("Press Ctrl+C to stop.")
    
    threads = []
    for i in range(THREAD_COUNT):
        t = threading.Thread(target=worker, name=f"Thread-{i+1}", daemon=True)
        t.start()
        threads.append(t)

    # Keep the main thread alive to allow background threads to run
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print("\nStopping...")

if __name__ == "__main__":
    main()
