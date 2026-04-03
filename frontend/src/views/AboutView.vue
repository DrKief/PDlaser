<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import http from "../api/http-client";

const stats = ref({ total: 0, tags: 0 });
const isLoaded = ref(false);

// Calculate approximate tensor index size based on float array sizes
const indexMemorySize = computed(() => {
  const bytesPerImage = 3831 * 4; // 3831 dimensions * 4 bytes (float32)
  const totalMegabytes = (stats.value.total * bytesPerImage) / (1024 * 1024);
  return totalMegabytes < 1 ? "< 1.0" : totalMegabytes.toFixed(1);
});

onMounted(async () => {
  try {
    const [imgRes, tagRes] = await Promise.all([
      http.get("/images?size=1"),
      http.get("/images/keywords"),
    ]);
    stats.value.total = imgRes.data.totalElements ?? 0;
    stats.value.tags = tagRes.data.length || 0;
  } catch (e) {
    console.error("Could not fetch stats", e);
  } finally {
    isLoaded.value = true;
  }
});
</script>

<template>
  <div class="view-wrapper">
    <div class="about-header text-center">
      <h1 class="page-title">System Architecture</h1>
      <p class="about-desc">
        PDLaser v3a is a high-performance, vector-based visual archiving system. It integrates
        advanced machine learning and pure mathematical matrices to execute deep similarity searches
        based on object semantics, structure, color, and intensity.
      </p>
    </div>

    <div class="stats-strip" :class="{ 'fade-in': isLoaded }">
      <div class="stat-box">
        <span class="stat-val">{{ stats.total }}</span>
        <span class="label-text">Images Indexed</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val">{{ stats.tags }}</span>
        <span class="label-text">Unique Tags</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val">3,831</span>
        <span class="label-text">Dimensions / Vector</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val"
          >{{ indexMemorySize }} <span style="font-size: 1.5rem">MB</span></span
        >
        <span class="label-text">Estimated Tensor Size</span>
      </div>
      <div class="divider-vertical"></div>
      <div class="stat-box">
        <span class="stat-val status-green">Online</span>
        <span class="label-text">System Status</span>
      </div>
    </div>

    <h3 class="section-heading label-text">Extraction Pipeline</h3>
    <div class="pipeline-container">
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">upload_file</div>
        <div class="step-content">
          <h4>1. Ingestion</h4>
          <p>Multipart payload intercepted and buffered in memory.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">aspect_ratio</div>
        <div class="step-content">
          <h4>2. Lanczos3</h4>
          <p>BoofCV downsamples spatial data to a strict 256x256 grid.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">memory</div>
        <div class="step-content">
          <h4>3. Extraction</h4>
          <p>ResNet-50 and standard vision algorithms map visual features.</p>
        </div>
      </div>
      <div class="pipeline-line"></div>
      <div class="pipeline-step">
        <div class="step-circle material-symbols-outlined">database</div>
        <div class="step-content">
          <h4>4. HNSW Index</h4>
          <p>L2 Normalized vectors injected into PostgreSQL pgvector.</p>
        </div>
      </div>
    </div>

    <h3 class="section-heading label-text">Engine Specifications</h3>
    <div class="tech-grid">
      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">hub</div>
        <h2>pgvector & HNSW</h2>
        <p>
          Utilizes PostgreSQL's <code>pgvector</code> extension for high-dimensional tensor storage.
          Queries are accelerated via Hierarchical Navigable Small World (HNSW) graphs, executing
          Cosine and L2 Distances natively.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">psychology</div>
        <h2>ResNet-50 AI Processing</h2>
        <p>
          Integrates Microsoft's ONNX Runtime to execute a localized ResNet-50 convolutional neural
          network. Extracts 1000-dimensional semantic logits entirely in-memory for contextual
          matching.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">grid_on</div>
        <h2>BoofCV Interpolation</h2>
        <p>
          Images are mathematically resampled using the precise
          <strong>Lanczos3</strong> algorithm before deep mathematical histograms (HOG, HSV, CIELAB)
          are extracted.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">speed</div>
        <h2>Virtual Threads (Java 21)</h2>
        <p>
          Built on Spring Boot 4. Heavy vector calculus and asynchronous I/O operations are
          delegated to non-blocking virtual thread pools, allowing massive concurrent extraction
          queues.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">layers</div>
        <h2>Vue 3 Reactive UI</h2>
        <p>
          The frontend architecture utilizes Vite, the Composition API, and strict TypeScript. State
          is managed ephemerally, with asynchronous polling maintaining UI consistency during
          background tasks.
        </p>
      </div>

      <div class="tech-card">
        <div class="card-icon material-symbols-outlined">dns</div>
        <h2>Containerized Micro-topology</h2>
        <p>
          Deployed via multi-stage Docker builds. The database, Java backend, and NGINX-served
          frontend operate in isolated alpine containers connected over a private bridge network.
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-header {
  max-width: 800px;
  margin: 0 auto 3rem auto;
  text-align: center;
}

.about-desc {
  font-size: 1.2rem;
  line-height: 1.6;
  color: var(--text-secondary);
}

.stats-strip {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem 3rem;
  margin-bottom: 4rem;
  opacity: 0;
  transform: translateY(10px);
  transition: all 0.5s ease;
  flex-wrap: wrap;
  gap: 2rem;
}
.stats-strip.fade-in {
  opacity: 1;
  transform: translateY(0);
}

.stat-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
  flex: 1;
  min-width: 150px;
}

.stat-val {
  font-family: var(--font-sans);
  font-size: 3rem;
  font-weight: 300;
  color: var(--color-accent);
  line-height: 1;
}

.status-green {
  color: var(--color-success);
  font-size: 2rem;
  font-weight: 500;
  letter-spacing: 1px;
}

.divider-vertical {
  width: 1px;
  height: 60px;
  background: var(--border-subtle);
  display: none;
}
@media (min-width: 1024px) {
  .divider-vertical {
    display: block;
  }
}

.section-heading {
  margin-bottom: 2rem;
  padding-bottom: 0.5rem;
  border-bottom: 1px solid var(--border-subtle);
  text-transform: uppercase;
  letter-spacing: 1px;
}

/* Pipeline CSS */
.pipeline-container {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  margin-bottom: 5rem;
  background: var(--bg-surface);
  padding: 3rem;
  border-radius: 8px;
  border: 1px solid var(--border-subtle);
  overflow-x: auto;
}
.pipeline-step {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  flex: 1;
  min-width: 150px;
  position: relative;
  z-index: 2;
}
.step-circle {
  width: 60px;
  height: 60px;
  background: var(--bg-element);
  border: 2px solid var(--color-accent);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  color: var(--text-primary);
  margin-bottom: 1rem;
  transition:
    transform 0.3s ease,
    background 0.3s ease;
}
.pipeline-step:hover .step-circle {
  transform: scale(1.1);
  background: var(--color-accent);
  color: var(--text-on-accent);
}
.step-content h4 {
  margin: 0 0 0.5rem 0;
  font-family: var(--font-sans);
}
.step-content p {
  margin: 0;
  font-size: 0.85rem;
  color: var(--text-secondary);
}
.pipeline-line {
  flex: 1;
  height: 2px;
  background: var(--border-strong);
  margin-top: 30px;
  min-width: 50px;
  position: relative;
}
.pipeline-line::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 0%;
  background: var(--color-accent);
  transition: width 0.5s ease;
}
.pipeline-container:hover .pipeline-line::after {
  width: 100%;
}

/* Tech Grid CSS */
.tech-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1.5rem;
}
@media (min-width: 768px) {
  .tech-grid {
    grid-template-columns: 1fr 1fr;
  }
}
@media (min-width: 1024px) {
  .tech-grid {
    grid-template-columns: 1fr 1fr 1fr;
  }
}

.tech-card {
  background: var(--bg-surface);
  border: 1px solid var(--border-subtle);
  border-radius: 8px;
  padding: 2rem;
  transition:
    transform 0.2s ease,
    border-color 0.2s ease,
    box-shadow 0.2s ease;
}
.tech-card:hover {
  transform: translateY(-4px);
  border-color: var(--border-strong);
  box-shadow: var(--shadow-subtle);
}

.card-icon {
  font-size: 2.5rem;
  color: var(--color-accent);
  margin-bottom: 1rem;
}

.tech-card h2 {
  font-size: 1.25rem;
  font-family: var(--font-sans);
  font-weight: 600;
  margin-bottom: 1rem;
}

.tech-card p {
  color: var(--text-secondary);
  line-height: 1.6;
  margin: 0;
  font-size: 0.95rem;
}
</style>
