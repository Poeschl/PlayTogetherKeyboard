<template>
  <div class="box is-fullwidth">
    <div class="is-flex is-flex-direction-column is-align-items-center">
      <div class="is-centered">Global pressed keys</div>
    </div>
    <BarChart :chart-data="data" :options="options" />
  </div>
</template>

<script setup lang="ts">
import { useDataStore } from "@/stores/DataStore";
import { computed } from "vue";
import { BarChart } from "vue-chart-3";
import { Chart, type ChartOptions, registerables } from "chart.js";
import useKeySanitizer from "@/services/KeySanitizer";

Chart.register(...registerables);

const dataStore = useDataStore();

const options: ChartOptions = {
  responsive: true,
  maintainAspectRatio: false,
  indexAxis: "y",
  scales: {
    x: {
      reverse: true,
      ticks: {
        autoSkip: false,
      },
    },
  },
  plugins: {
    legend: {
      display: false,
    },
  },
};

const data = computed(() => {
  const keys = dataStore.keyStatistics.keys;

  const labels = keys.map((key) => useKeySanitizer().sanitizeLabel(key.key.key));
  const data = keys.map((key) => key.presses);

  return { labels: labels, datasets: [{ data: data, backgroundColor: "#b74b4b" }] };
});
</script>

<style scoped lang="scss">
.box {
  height: 30rem;
}
</style>
