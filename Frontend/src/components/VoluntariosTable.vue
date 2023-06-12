<template>
  <table class="data-table">
    <thead>
      <tr>
        <th v-for="(header, key) in headers" :key="key">{{ header }}</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(data, key) in dataSet" :key="key">
        <td>{{ data.id }}</td>
        <td>{{ data.nombre }}</td>
        <td>{{ data.correo }}</td>
      </tr>
    </tbody>
  </table>
</template>
  
<script lang="ts">
import type Voluntario from "@/models/Voluntario";
import { defineComponent } from "vue";
export default defineComponent({
  name: "EmergenciasTable",
  data() {
    return {
      headers: ["ID", "NOMBRE", "CORREO"],
    };
  },

  props: {
    dataSet: { type: Array<Voluntario>, required: true },
  },
  methods: {},
});
</script>
  
<style scoped>
.data-table {
  border-collapse: collapse;
  border-radius: 15px;
  overflow: hidden;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  font-family: "Open Sans";
  font-size: 18px;
  text-align: left;
  margin: 0 150px 60px 150px;
}

.data-table thead {
  background-color: #ff5c39;
  color: white;
  border-bottom: 1px solid #ababab;
}

.data-table thead tr th {
  font-weight: 700;
}

.data-table th,
td {
  padding: 18px 25px;
}

.data-table tbody tr {
  background-color: #dadada;
  border-bottom: 1px solid #ababab;
}

.data-table tbody tr td {
  font-weight: 600;
  font-size: 16px;
}

.data-table thead tr th:first-child {
    min-width: 80px;
}

.data-table thead tr th:nth-child(2) {
    min-width: 250px;
}

.data-table thead tr th:nth-child(3) {
    min-width: 350px;
}

.data-table tbody tr:nth-of-type(even) {
  background-color: #f3f3f3;
}
</style>
