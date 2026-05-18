import { ref } from "vue";

export interface Image {
  id: number | string;
  uploader?: string;
  keywords: { keyword: string; isAi?: boolean }[];
  extraction_status?: string;
  url?: string;
}

const similarityResultsOverride = ref<Image[] | null>(null);
const similaritySourceOverride = ref<Image | null>(null);
const selectedSourceId = ref<number | null>(null);

export function useGallerySearch() {
  const clearSimilaritySearch = () => {
    similarityResultsOverride.value = null;
    similaritySourceOverride.value = null;
    selectedSourceId.value = null;
  };

  return {
    similarityResultsOverride,
    similaritySourceOverride,
    selectedSourceId,
    clearSimilaritySearch,
  };
}
