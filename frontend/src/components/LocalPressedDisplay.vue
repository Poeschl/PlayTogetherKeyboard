<template>
  <div class="box is-fullwidth">
    <div class="is-flex is-flex-direction-column is-align-items-center">
      <div class="is-centered">Detected keystrokes</div>
    </div>
    <div class="letterArea columns is-fullwidth">
      <div v-for="slot in dropSlots" class="column" :class="{ animating: slot.length > 0 }" :style="{ 'animation-delay': Math.random() * 200 + 'ms' }">
        <div class="is-size-4">
          {{ slot }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const dropSlots = ref<string[]>(["", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""]);

const newKey = (key: string) => {
  let processed = false;
  let trys = 5;
  while (!processed && trys >= 0) {
    const randomSlot = Math.round(Math.random() * 11);
    if (dropSlots.value[randomSlot] == "") {
      dropSlots.value[randomSlot] = key;
      processed = true;

      setTimeout(() => (dropSlots.value[randomSlot] = ""), 800);
    }
    trys--;
  }
};

defineExpose({ newKey });
</script>

<style scoped lang="scss">
@keyframes vanish {
  from {
    opacity: 1;
  }

  to {
    opacity: 0;
  }
}

.letterArea {
  min-height: 2.3rem;

  .animating {
    opacity: 0;
    animation: vanish 600ms ease-in;
  }
}
</style>
