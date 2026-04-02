import { ref } from 'vue';
import http from '../api/http-client';
import { useImageStatus } from './useImageStatus';

export interface UploadTask {
  internalId: string;
  file: File;
  previewUrl: string;
  id?: number;
  status: 'PENDING' | 'UPLOADING' | 'EXTRACTING' | 'COMPLETED' | 'FAILED' | 'DUPLICATE';
}

export function useUploadQueue() {
  const tasks = ref<UploadTask[]>([]);
  const tagsList = ref<string[]>([]);
  const isUploading = ref(false);
  const globalMessage = ref("");
  const { pollStatus, statusCache } = useImageStatus();

  const onFileChange = (event: Event) => {
    const target = event.target as HTMLInputElement;
    if (!target.files || target.files.length === 0) return;
    const incomingFiles = Array.from(target.files);
    const availableSlots = 10 - tasks.value.length;
    
    if (incomingFiles.length > availableSlots) {
      globalMessage.value = `Maximum 10 images. Keeping the first ${availableSlots} files.`;
    } else {
      globalMessage.value = "";
    }
    
    const filesToAdd = incomingFiles.slice(0, availableSlots);
    filesToAdd.forEach(file => {
      tasks.value.push({
        internalId: Math.random().toString(36).substring(7),
        file,
        previewUrl: URL.createObjectURL(file),
        status: 'PENDING'
      });
    });
    target.value = '';
  };

  const removeTaskById = (iId: string) => {
    const index = tasks.value.findIndex(t => t.internalId === iId);
    if (index > -1) {
      const task = tasks.value[index]!;
      if (isUploading.value && task.status === 'UPLOADING') return;
      URL.revokeObjectURL(task.previewUrl);
      tasks.value.splice(index, 1);
    }
  };

  const clearAll = () => {
    tasks.value.forEach(t => URL.revokeObjectURL(t.previewUrl));
    tasks.value = [];
    tagsList.value = [];
    globalMessage.value = "";
  };

  const executeUpload = async () => {
    if (tasks.value.length === 0) return;
    isUploading.value = true;
    globalMessage.value = "Uploading batch...";
    
    for (let i = tasks.value.length - 1; i >= 0; i--) {
      const task = tasks.value[i]!;
      if (task.status !== 'PENDING' && task.status !== 'FAILED') continue;
      
      task.status = 'UPLOADING';
      const formData = new FormData();
      formData.append("file", task.file);
      
      // Send array properly
      if (tagsList.value.length > 0) {
        tagsList.value.forEach(tag => formData.append("keywords", tag));
      }

      try {
        const response = await http.post("/images", formData, { headers: { "Content-Type": "multipart/form-data" } });
        const id = response.data.id || response.data;
        task.id = id;
        task.status = 'EXTRACTING';
        
        pollStatus(id).then(() => {
          if (statusCache[id] === 'COMPLETED') {
            task.status = 'COMPLETED';
            setTimeout(() => removeTaskById(task.internalId), 3500);
          }
          if (statusCache[id] === 'FAILED') task.status = 'FAILED';
        });
      } catch (e: any) {
        if (e.response?.status === 409) {
          task.status = 'DUPLICATE';
          setTimeout(() => removeTaskById(task.internalId), 3500);
        } else {
          task.status = 'FAILED';
        }
      }
    }
    globalMessage.value = "Batch uploaded.";
    isUploading.value = false;
  };

  return { tasks, tagsList, isUploading, globalMessage, statusCache, executeUpload, onFileChange, removeTaskById, clearAll };
}