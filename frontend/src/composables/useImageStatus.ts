import { reactive } from 'vue';
import http from '../http-api';

const statusCache = reactive<Record<number, string>>({});
const pollingIds = new Set<number>();

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

  const pollStatus = async (id: number, intervalMs = 500, maxAttempts = 20) => {
    if (pollingIds.has(id)) return;
    pollingIds.add(id);

    try {
      let attempts = 0;
      while (attempts < maxAttempts) {
        // If status is already successfully fully COMPLETEDd before fetching again, we can abort
        if (statusCache[id] === 'COMPLETED' || statusCache[id] === 'FAILED') {
          break;
        }
        
        const status = await fetchStatus(id);
        // Assuming backend returns status like "PENDING", "COMPLETEDD", "FAILED"
        if (!status || status === 'COMPLETED' || status === 'FAILED') {
          break;
        }
        attempts++;
        await new Promise(resolve => setTimeout(resolve, intervalMs));
      }
    } finally {
      pollingIds.delete(id);
    }
  };

  return {
    statusCache,
    getStatus,
    fetchStatus,
    pollStatus
  };
}
