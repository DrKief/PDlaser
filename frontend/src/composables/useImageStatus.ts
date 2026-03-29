import { reactive } from "vue";
import http from "../http-api";

const statusCache = reactive<Record<number, string>>({});
const activeConnections = new Set<number>();

export function useImageStatus() {
  const getStatus = (id: number) => statusCache[id];

  const fetchStatus = async (id: number) => {
    try {
      const response = await http.get(`/images/${id}/status`);
      const status = response.data.extraction_status;
      statusCache[id] = status;
      return status;
    } catch (e) {
      console.error(`Failed to fetch status for image ${id}`);
      return null;
    }
  };

  const pollStatus = async (id: number) => {
    if (activeConnections.has(id)) return;
    activeConnections.add(id);

    try {
      while (activeConnections.has(id)) {
        if (statusCache[id] === "COMPLETED" || statusCache[id] === "FAILED") {
          break;
        }

        // This will now "hang" securely until the server returns an update or a timeout triggers.
        const response = await http.get(`/images/${id}/status`);
        const status = response.data.extraction_status;
        statusCache[id] = status;

        if (status === "COMPLETED" || status === "FAILED") {
          break;
        }
      }
    } catch (e) {
      console.error(`Error in long polling for image ${id}`, e);
    } finally {
      activeConnections.delete(id);
    }
  };

  return {
    statusCache,
    getStatus,
    fetchStatus,
    pollStatus,
  };
}
