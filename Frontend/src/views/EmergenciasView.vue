<template>
  <main class="emergencias">
    <h2 class="titulo">Listado de emergencias</h2>
    <EmergenciasTable @changeStatus="switchStatus" :dataSet="tableDataSet" />
  </main>
</template>

<script lang="ts">
import axios from "axios";
import { defineComponent } from "vue";
import { useAuthStore } from "@/stores/auth";
import EmergenciasTable from "@/components/EmergenciasTable.vue";
import type Emergencia from "@/models/Emergencia";
export default defineComponent({
  components: { EmergenciasTable },
  name: "Emergencias",
  data() {
    return {
      tableDataSet: [] as Array<Emergencia>,
    };
  },

  methods: {
    getEmergencias() {
      const authStore = useAuthStore();
      const token = authStore.token;
      axios
        .get("/api/emergencias/extra", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then((response) => {
          this.tableDataSet = this.jsonArrayToEmergencias(response.data);
        });
    },
    jsonArrayToEmergencias(json: Array<any>): Array<Emergencia> {
      const emergencias: Array<Emergencia> = [];

      // Convertir el JSON a objetos Emergencia
      for (let i = 0; i < json.length; i++) {
        const emergencia: Emergencia = {
          id: json[i].idEmergencia,
          institucion: json[i].nombreInstitucion,
          nombre: json[i].nombre,
          descripcion: json[i].descripcion,
          fecha_inicio: json[i].fecha_inicio,
          fecha_termino: json[i].fecha_termino,
          activo: json[i].activo,
          tareas_activas: json[i].tareasActivas,
        };
        emergencias.push(emergencia);
      }

      emergencias.sort((a, b) => a.id - b.id);

      return emergencias;
    },

    switchStatus(id: number, status: boolean, key: number) {
      const authStore = useAuthStore();
      const token = authStore.token;
      axios
        .put(
          `/api/emergencias/${id}`,
          { activo: !status },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        )
        .then((response) => {
          if(response.status == 200 && response.data) {
            this.tableDataSet[key].activo = response.data.activo
          }
        });
    },
  },
  mounted() {
    this.getEmergencias();
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
