import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/Home/HomeView.vue";
import FormView from "../views/Form/FormView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/novo",
      name: "novo",
      component: FormView,
    },
    {
      path: "/editar/:id",
      name: "editar",
      component: FormView,
      props: true,
    },
  ],
});

export default router;
