import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from "@/stores/auth";
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import EmergenciasView from '../views/EmergenciasView.vue'
import TareasView from '@/views/TareasView.vue';
import VoluntariosRadioVue from '@/views/VoluntariosRadio.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/emergencias',
      name: 'emergencias',
      component: EmergenciasView
    },
    {
      path: '/tareas',
      name: 'tareas',
      component: TareasView
    },
    {
      path: '/emergencias/:id',
      name: 'voluntariosRadio',
      component: VoluntariosRadioVue

    }

  ]
})

// Filtro para rutas no autorizadas para usuarios no autenticados
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  const isAuthenticated = authStore.isAuthenticated
  const authorizedRoutes = ["/", "/login", "/register"]
  const isAuthorizedRoute = !authorizedRoutes.every(route => route != to.path)
  if(!isAuthorizedRoute && !isAuthenticated) {
    alert("Esta página es solo para usuarios registrados. Por favor, inicia sesión.")
    next("/login")
  }
  else if (to.path != "/" && isAuthorizedRoute && isAuthenticated){
    next("/")
  }
  else {
    next()
  }
})

export default router
