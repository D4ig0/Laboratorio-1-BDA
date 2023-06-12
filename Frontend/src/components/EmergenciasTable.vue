
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
        <td>{{ data.institucion }}</td>
        <td>{{ data.nombre }}</td>
        <td>{{ data.descripcion }}</td>
        <td>{{ data.fecha_inicio }}</td>
        <td>{{ data.fecha_termino }}</td>

        <td class="status">
          <button
            @click="changeStatus(data.id, data.activo, key)"
            :class="statusByBoolean(data.activo)"
          >
            {{ statusByBoolean(data.activo).toUpperCase() }}
          </button>
        </td>
        <td>{{ data.tareas_activas }}</td>
        <td class="span-td">
          <span @click="redirect(data.id)" class="material-symbols-rounded">
            arrow_forward_ios
          </span>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script lang="ts">
import type Emergencia from "@/models/Emergencia";
import router from "@/router";
import { defineComponent } from "vue";
export default defineComponent({
  name: "EmergenciasTable",
  data() {
    return {
      headers: [
        "ID",
        "INSTITUCIÓN",
        "NOMBRE",
        "DESCRIPCIÓN",
        "FECHA INICIO",
        "FECHA TERMINO",
        "ESTADO",
        "TAREAS ACTIVAS",
        "",
      ],
    };
  },
  props: {
    dataSet: { type: Array<Emergencia>, required: true },
  },
  methods: {
    statusByBoolean(bool: boolean): string {
      return bool ? "activa" : "inactiva";
    },

    changeStatus(id: number, status: boolean, key: number) {
      this.$emit("changeStatus", id, status, key);
    },

    redirect(id: number) {
      router.push(`/emergencias/${id}`);
    },
  },
});
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200");

.data-table span.material-symbols-rounded {
  font-family: "Material Symbols Rounded";
  font-weight: 600;
  font-size: 24px;
  cursor: pointer;
  margin-top: 5px;
}

.data-table .span-td {
  padding: 0 !important;
  justify-items: center;
}

.data-table span.material-symbols-rounded:hover {
  color: #ec411a;
}

.data-table {
  min-width: 1000px;
  border-collapse: collapse;
  border-radius: 15px;
  overflow: hidden;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  text-align: left;
  font-family: "Open Sans";
  font-size: 18px;
  margin-bottom: 60px;
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

.data-table tbody tr:nth-of-type(even) {
  background-color: #f3f3f3;
}

.status {
  min-width: 140px;
}

.status button {
  padding: 4px 10px;
  border: none;
  text-decoration: none;
  border-radius: 2px;
  cursor: pointer;
  font-family: "Open Sans";
  font-weight: 600;
  font-size: 14px;
  color: white;
}

.data-table tbody .activa {
  background: green;
}

.data-table tbody .activa:hover {
  background: rgb(0, 110, 0);
}

.data-table tbody .inactiva {
  background: red;
}

.data-table tbody .inactiva:hover {
  background: rgb(200, 0, 0);
}
</style>
