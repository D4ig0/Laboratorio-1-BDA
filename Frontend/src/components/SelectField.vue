<template>
  <select class="select-field" @change="onChange" name="select">
    <option selected disabled>{{ nombre }}</option>
    <option v-for="(item, key) in items" :key="key" :value="item.id">
      {{ item.nombre }}
    </option>
  </select>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "SelectField",
  props: {
    nombre: { type: String, required: true },
    items: { type: Array<any>, required: true },
  },
  methods: {
    onChange(event: any) {
      this.$emit("selected", event.target.value);
    },
  },
});
</script>

<style>
.select-field {
  border-radius: 0.2rem;
  border: 0.1rem solid #363225;
  background-color: #ffffff;
  padding: 0.5rem;
  width: 350px;
  outline: none;
  font-family: "Open Sans";
  font-size: 14px;
  font-weight: 600;
  color: #363225;
}
</style>