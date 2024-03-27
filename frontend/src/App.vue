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
        <div class="level-left">
          <div class="level-item">
            {{ pressedKeys }}
          </div>
        </div>
        <div class="level-right">
          <div class="level-item is-flex is-flex-direction-column">
            <div>{{ JSON.stringify(dataStore.keyStatistics.keys) }}</div>
            <div>{{ JSON.stringify(dataStore.lastPressedKey) }}</div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import NavBar from "@/components/NavBar.vue";
import { useDataStore } from "@/stores/DataStore";
import { onMounted, ref } from "vue";
import { useMagicKeys } from "@vueuse/core";
import type { Key } from "@/models/main";

const dataStore = useDataStore();

const pressedKeys = ref<string[]>([]);

onMounted(() => {
  dataStore.initWebsocket();
  useMagicKeys({ onEventFired: onKey });
});

const onKey = (keyEvent: KeyboardEvent) => {
  if (keyEvent.type == "keydown") {
    const key: Key = { code: keyEvent.code, key: keyEvent.key };
    dataStore.sendKeyStroke(key);
    visualiseKey(key);
  }
};

const visualiseKey = (key: Key) => {
  const updatedList = pressedKeys.value;
  updatedList.push(key.key);
  pressedKeys.value = [];
  pressedKeys.value = updatedList;
};
</script>
