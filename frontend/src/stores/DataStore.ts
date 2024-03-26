import { defineStore } from "pinia";
import { useWebSocket, WebSocketTopic } from "@/services/WebsocketService";
import type { Key, KeyStatistics } from "@/models/main";
import { ref } from "vue";

export const useDataStore = defineStore("dataStore", () => {
  const keyStatistics = ref<KeyStatistics>({ keys: [] });

  const updateStats = (newKeyStatistics: KeyStatistics) => {
    keyStatistics.value = newKeyStatistics;
  };

  const initWebsocket = () => {
    const websocketService = useWebSocket();
    websocketService.initWebsocket();
    websocketService.registerForTopicCallback(WebSocketTopic.STATISTIC_TOPIC, updateStats);
  };

  const sendKeyType = (key: Key) => {
    useWebSocket().sendKeypress(key);
  };

  return { initWebsocket, keyStatistics, sendKeyType };
});
