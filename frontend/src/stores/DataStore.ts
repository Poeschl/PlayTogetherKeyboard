import { defineStore } from "pinia";
import { useWebSocket, WebSocketTopic } from "@/services/WebsocketService";
import type { Key, KeyStatistics } from "@/models/main";
import { ref } from "vue";

const websocketService = useWebSocket();

export const useDataStore = defineStore("dataStore", () => {
  const keyStatistics = ref<KeyStatistics>({ keys: [] });

  const updateStats = (newKeyStatistics: KeyStatistics) => {
    keyStatistics.value = newKeyStatistics;
  };

  const initWebsocket = () => {
    websocketService.initWebsocket();
    websocketService.registerForTopicCallback(WebSocketTopic.STATISTIC_TOPIC, updateStats);
  };

  const sendKeyStroke = (key: Key) => {
    websocketService.sendKeypress(key);
  };

  return { initWebsocket, keyStatistics, sendKeyStroke };
});
