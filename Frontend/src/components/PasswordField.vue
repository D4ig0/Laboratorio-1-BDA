<template>
  <div class="password-field">
    <label for="password">Contraseña<br /></label>
    <input
      id="password"
      @input="sendPassword"
      v-model="password"
      type="password"
      required
    />
    <span
      @click="changeVisibility"
      id="eye-icon"
      class="material-symbols-outlined"
      >visibility_off</span
    >
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "PasswordField",
  data() {
    return {
      password: "",
    };
  },
  methods: {
    sendPassword() {
      this.$emit("passwordData", this.password);
    },

    changeVisibility() {
      this.changeInputType();
      this.changeEyeIcon();
    },

    changeInputType() {
      const input = document.getElementById("password");
      const inputType = input?.getAttribute("type");
      const newInputType = inputType === "password" ? "text" : "password";
      input?.setAttribute("type", newInputType);
    },

    changeEyeIcon() {
      const eyeIcon = document.getElementById("eye-icon");
      if (eyeIcon != null) {
        const acutalIcon = eyeIcon.textContent;
        const newIcon = acutalIcon?.includes("visibility_off")
          ? "visibility"
          : "visibility_off";
        eyeIcon.textContent = newIcon;
      }
    },
  },
});
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@40,400,0,0");
.password-field label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}

.password-field input {
  border-radius: 0.2rem;
  border: 0.1rem solid #363225;
  background-color: transparent;
  padding: 0.5rem;
  color: #363225;
  width: 25rem;
  outline: none;
  font-family: "Open Sans";
  font-weight: 500;
  color: #363225;
}

.password-field input[type="password"]::-ms-reveal,
input[type="password"]::-ms-clear {
  display: none;

}

.password-field .material-symbols-outlined {
  font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 32;
  position: absolute;
  top: 1.9rem;
  right: 0.5rem;
  cursor: pointer;
}
</style>