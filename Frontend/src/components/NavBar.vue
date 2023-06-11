<template>
  <nav class="navbar">
    <div class="links left">
      <LinkContainer text="INICIO" path="/" direction="left" />
      <LinkContainer v-if="authStore.isAuthenticated" text="EMERGENCIAS" path="/emergencias" direction="left" />
      <LinkContainer v-if="authStore.isAuthenticated" text="TAREAS" path="/tareas" direction="left" />
    </div>
    <div v-if="!authStore.isAuthenticated" class="links right">
      <LinkContainer text="INICIAR SESIÓN" path="/login" direction="right" />
      <LinkContainer text="REGISTRARSE" path="/register" direction="right" />
    </div>
    <div v-else class="user-dropdown">
      <div class="dropdown-header">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          height="30"
          viewBox="0 96 960 960"
          width="30"
          fill="#221f17"
        >
          <path
            d="M479.968 571.906q-68.982 0-114.49-45.508-45.507-45.507-45.507-114.489t45.507-114.481q45.508-45.5 114.49-45.5 68.981 0 114.601 45.5 45.619 45.499 45.619 114.481t-45.619 114.489q-45.62 45.508-114.601 45.508Zm250.974 334.217H229.058q-31.494 0-53.624-22.213-22.13-22.214-22.13-53.54v-31.42q0-38.413 19.525-67.525 19.524-29.112 50.888-44.294 65.892-30.573 129.012-45.899 63.121-15.326 127.228-15.326 64.566 0 127.326 15.62 62.76 15.619 128.28 45.655 32.274 15.045 51.783 44.077 19.509 29.032 19.509 67.692v31.42q0 31.326-22.177 53.54-22.176 22.213-53.736 22.213ZM229.058 830.37h501.884v-30.384q0-14.153-8.166-26.672-8.167-12.519-20.501-18.77-59.55-28.87-112.579-40.798-53.028-11.927-109.775-11.927-56.269 0-110.283 11.927-54.015 11.928-112.512 40.773-12.402 6.252-20.235 18.779-7.833 12.526-7.833 26.688v30.384Zm250.907-334.218q35.999 0 60.155-24.128 24.156-24.129 24.156-60.139 0-36.076-24.121-60.14t-60.12-24.064q-35.999 0-60.155 24.086t-24.156 60.036q0 36.05 24.121 60.2 24.121 24.149 60.12 24.149Zm.035-84.275Zm0 418.493Z"
          />
        </svg>
        <p>{{ authUser.name.toUpperCase() }}</p>
      </div>
      <div class="dropdown-content">
        <li>
          <a @click="onLogOut">Cerrar Sesión</a>
        </li>
      </div>
    </div>
  </nav>
</template>

<script lang="ts">
import { useAuthStore } from "@/stores/auth";
import { defineComponent } from "vue";
import router from "@/router";
import LinkContainer from "./LinkContainer.vue";

export default defineComponent({
  components: { LinkContainer },
  name: "NavBar",
  setup() {
    const authStore = useAuthStore();
    const authUser = authStore.user;
    return {
      authStore,
      authUser,
    };
  },

  methods: {
    onLogOut(){
      this.authStore.logout()
      router.replace({ path: "/" });
    }
  }
});
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  margin: 0 120px;
}

.links {
  box-sizing: border-box;
  display: flex;
  gap: 25px;
}

.user-dropdown {
  display: grid;
  justify-items: end;
}

.dropdown-header {
  display: flex;
  align-items: center;
  cursor: pointer;
  gap: 12px;
}

.dropdown-header svg {
  margin-bottom: 5px;
}

.dropdown-header p {
  font-family: "Open Sans";
  font-weight: 700;
  font-size: 16px;
  color: #363225;
}

.dropdown-content {
  display: none;
  position: absolute;
  list-style: none;
  background-color: #c4beb0;
  border-radius: 6px;
  min-width: 140px;
  box-shadow: 0px 8px 16px 0px rgba(78, 78, 78, 0.5);
  padding: 12px 20px 4px 20px;
  z-index: 1;
  top: 50px;
}

.dropdown-content li {
  margin-bottom: 8px;
}

.dropdown-content li a {
  display: flex;
  width: 100%;
  color: #363225;
  font-family: "Open Sans";
  font-weight: 600;
  font-size: 15px;
  cursor: pointer;
}

.user-dropdown:hover .dropdown-content {
  display: block;
}
</style>