<template>
  <div data-theme="dark">
    <NavBar class="mb-5" />
    <main class="container">
      <div class="level mb-6">
        <div class="level-item has-text-centered">
          <p>
            Any keypress on the letter keys and arrow keys will get transmitted to the host. They will be collected and the key with the most strokes will be
            executed.
          </p>
        </div>
      </div>
      <div class="level">
        <div class="level-left"></div>
        <div class="level-right">
          {{ JSON.stringify(dataStore.keyStatistics.keys) }}
        </div>
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
import { useDataStore } from "@/stores/DataStore";
import { onMounted } from "vue";

const dataStore = useDataStore();

onMounted(() => {
  dataStore.initWebsocket();
});
</script>
