<template>
  <div class="box is-fullwidth">
    <div class="is-flex is-flex-direction-column is-align-items-center">
      <div class="is-centered">Last pressed key on game</div>
      <div class="is-size-1">{{ lastPress }}</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useDataStore } from "@/stores/DataStore";
import { computed } from "vue";
import useKeySanitizer from "@/services/KeyLabelConverter";

const dataStore = useDataStore();

const lastPress = computed<string>(() => {
  if (dataStore.lastPressedKey) {
    return useKeySanitizer().sanitizeLabel(dataStore.lastPressedKey.key);
  } else {
    return "";
  }
});
</script>

<style scoped lang="scss"></style>
