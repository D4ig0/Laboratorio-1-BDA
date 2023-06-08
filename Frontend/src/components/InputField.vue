<template>
  <div class="input-field">
    <label :for="fieldName">{{ fieldName }}<br /></label>
    <input
      @input="sendInputData"
      :id="fieldName"
      v-model="input"
      :type="inputType"
      required
    />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "PasswordField",
  props: {
    fieldName: { type: String, required: true },
    inputType: { type: String, required: true },
  },
  data() {
    return {
      input: "",
    };
  },
  methods: {
    sendInputData() {
      this.$emit("inputData", this.input);
    },
  },
});
</script>

<style>
.input-field label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #363225;
}

.input-field input {
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
</style>