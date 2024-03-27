import { Client, Versions } from "@stomp/stompjs";
import log from "loglevel";
import type { Key, KeyStatistics } from "@/models/main";

export enum WebSocketTopic {
  STATISTIC_TOPIC = 0,
  LAST_PRESSED_KEY_TOPIC,
}

export function useWebSocket() {
  const websocketPath = "/ws";
  const statisticTopic = "/topic/stats";
  const lastPressedTopic = "/topic/lastPressed";
  const keyPressTopic = "/app/keypress";
  const updateTopic = "/app/update";
  const topicListener = new Map<WebSocketTopic, Function>();
  let websocketClient: Client | undefined = undefined;

  const initWebsocket = () => {
    websocketClient = createClient();

    websocketClient.onConnect = () => {
      log.info("Websocket connected");
      connectToTopics(websocketClient!!);
      sendUpdateRequest();
    };

    websocketClient.activate();
  };

  const registerForTopicCallback = (topic: WebSocketTopic, callback: Function) => {
    topicListener.set(topic, callback);
  };

  const createClient = (): Client => {
    const protocol = location.protocol == "https:" ? "wss" : "ws";
    const websocketUrl = `${protocol}://${location.host}${websocketPath}`;

    return new Client({
      brokerURL: websocketUrl,
      stompVersions: new Versions(["1.2"]),

      onWebSocketError: (error) => {
        log.error(`Error with websocket (${JSON.stringify(error)})`);
      },

      onStompError: (frame) => {
        log.error(`Broker reported error: ${frame.headers["message"]}`);
        log.error(`Additional details: ${frame.body}`);
      },

      onWebSocketClose: (closeEvent: CloseEvent) => {
        log.error("Websocket disconnected.");
      },
    });
  };

  const connectToTopics = (client: Client) => {
    client.subscribe(statisticTopic, (message) => {
      const keyStats: KeyStatistics = JSON.parse(message.body);
      topicListener.get(WebSocketTopic.STATISTIC_TOPIC)?.call(null, keyStats);
    });

    client.subscribe(lastPressedTopic, (message) => {
      const key: Key = JSON.parse(message.body);
      topicListener.get(WebSocketTopic.LAST_PRESSED_KEY_TOPIC)?.call(null, key);
    });
  };

  const sendUpdateRequest = () => {
    if (websocketClient?.active) {
      websocketClient.publish({ destination: updateTopic });
    } else {
      log.warn("Could not request update. WS client not connected!");
    }
  };

  const sendKeypress = (key: Key) => {
    if (websocketClient?.active) {
      websocketClient.publish({ destination: keyPressTopic, body: JSON.stringify(key) });
    } else {
      log.warn("Could not send keystroke. WS client not connected!");
    }
  };

  return { initWebsocket, registerForTopicCallback, sendKeypress };
}
