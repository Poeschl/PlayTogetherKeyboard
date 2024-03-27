import { defineStore } from "pinia";
import { useWebSocket, WebSocketTopic } from "@/services/WebsocketService";
import type { Key, KeyStatistics } from "@/models/main";
import { ref } from "vue";

const websocketService = useWebSocket();

export const useDataStore = defineStore("dataStore", () => {
  const keyStatistics = ref<KeyStatistics>({ keys: [] });
  const lastPressedKey = ref<Key>();

  const updateStats = (newKeyStatistics: KeyStatistics) => {
    keyStatistics.value = newKeyStatistics;
  };

  const updateLastPressedKey = (key: Key) => {
    lastPressedKey.value = key;
  };

  const initWebsocket = () => {
    websocketService.initWebsocket();
    websocketService.registerForTopicCallback(WebSocketTopic.STATISTIC_TOPIC, updateStats);
    websocketService.registerForTopicCallback(WebSocketTopic.LAST_PRESSED_KEY_TOPIC, updateLastPressedKey);
  };

  const sendKeyStroke = (key: Key) => {
    websocketService.sendKeypress(key);
  };

  return { keyStatistics, lastPressedKey, initWebsocket, sendKeyStroke };
});
