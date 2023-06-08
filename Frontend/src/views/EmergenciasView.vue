<template>
  <main class="emergencias">
    <h2 class="titulo">Listado de emergencias</h2>
    <EmergenciasTable :headers="tableHeaders" :dataSet="tableDataSet" />
  </main>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import EmergenciasTable from "@/components/EmergenciasTable.vue";
export default defineComponent({
  components: { EmergenciasTable },
  name: "Emergencias",
  data() {
    return {
      tableHeaders: [
        "ID",
        "INSTITUCIÓN",
        "NOMBRE",
        "DESCRIPCIÓN",
        "FECHA INICIO",
        "FECHA TERMINO",
        "ESTADO",
        "TAREAS ACTIVAS",
      ],
      tableDataSet: [
        {
          id: 1,
          institucion: "Institucion 01",
          nombre: "Emergencia 01",
          descripcion: "descripción test",
          fecha_inicio: "2020-05-20",
          fecha_termino: "2020-06-30",
          activo: true,
          tareas_activas: 8,
        },
        {
          id: 2,
          institucion: "Institucion 01",
          nombre: "Emergencia 02",
          descripcion: "descripción test",
          fecha_inicio: "2023-11-01",
          fecha_termino: "2023-12-08",
          activo: true,
          tareas_activas: 3,
        },
        {
          id: 3,
          institucion: "Institucion 02",
          nombre: "Emergencia 03",
          descripcion: "descripción test",
          fecha_inicio: "2020-12-01",
          fecha_termino: "2020-12-03",
          activo: false,
          tareas_activas: 0,
        },
        {
          id: 4,
          institucion: "Institucion 01",
          nombre: "Emergencia 04",
          descripcion: "descripción test",
          fecha_inicio: "2022-04-01",
          fecha_termino: "2022-08-23",
          activo: true,
          tareas_activas: 15,
        },
      ],
    };
  },
  methods: {

  },
});
</script>

<style>
.emergencias {
  display: grid;
  justify-items: center;
  margin-top: 3rem;
  gap: 2rem;
}

.emergencias .titulo {
  font-weight: 700;
  font-size: 1.5rem;
  color: #363225;
}
</style>
