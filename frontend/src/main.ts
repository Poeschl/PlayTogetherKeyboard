import { createApp } from "vue";
import "./assets/main.scss";
import App from "./App.vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { createPinia } from "pinia";
import { useLogging } from "@/config/logging";
import { faGithub } from "@fortawesome/free-brands-svg-icons";

const app = createApp(App);

library.add(faGithub);
app.component("FontAwesomeIcon", FontAwesomeIcon);

// Use pinia for local state storing
const pinia = createPinia();
app.use(pinia);

useLogging().setup();
app.mount("#app");
